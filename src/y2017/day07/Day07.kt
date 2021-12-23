package y2017.day07

import y2017.readInput

const val day = "07"
fun main() {
    fun part1(input: List<String>): String = with(input.map { it.split(" -> ")[0].split(" ")[0] to Program(it) }.toMap()) {
        values.forEach { it.setChildren(this) }
        values.first().getRoot().name
    }

    fun part2(input: List<String>): Int = with(input.map { it.split(" -> ")[0].split(" ")[0] to Program(it) }.toMap()) {
        values.forEach { it.setChildren(this) }

        values.first(Program::unbalanced).balancing.correctWeight()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test").filter(String::isNotBlank)
    check(part1(testInput) == "tknk")
    check(part2(testInput) == 60)

    val input = readInput("day$day\\Day$day").filter(String::isNotBlank)
    println(part1(input))
    println(part2(input))
}

class Program(input: String) {
    val name = input.split(" ")[0]
    val weight = input.split("(")[1].split(")")[0].toInt()
    private val balancedNames = if (input.contains(" -> ")) input.split(" -> ")[1].split(", ") else emptyList()
    val balancing = mutableListOf<Program>()
    var parent: Program? = null
    fun setChildren(programs: Map<String, Program>) = balancedNames.forEach {
        val p = programs[it]!!
        p.parent = this
        balancing.add(p)
    }

    fun towerWeight(): Int = weight + balancing.sumOf(Program::towerWeight)
    fun unbalanced() = balancing.map(Program::towerWeight).toSet().size > 1
    fun getRoot(): Program = parent?.getRoot() ?: this
}

fun List<Program>.correctWeight(): Int = groupBy { it.towerWeight() }.entries.partition { (_, v) -> v.size == 1 }.let { it.first.first().value.first() to it.second.first().value.first() }.let { it.first.weight + it.second.towerWeight() - it.first.towerWeight() }
