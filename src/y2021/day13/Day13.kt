package y2021.day13

import y2021.readInput

const val day = "13"
fun main() {
    fun part1(input: List<String>): Int {
        val seperator = input.indexOf("")
        val (a, b) = input.partition { input.indexOf(it) < seperator }.let { it.first.map { l -> l.split(",").map(String::toInt).let { p -> p[0] to p[1] } } to it.second[1].split("along ")[1] }
        val (hor, num) = b.split("=").let { (it[0] == "y") to (it[1].toInt()) }
        return a.map { if (hor && it.second > num) it.first to (2 * num - it.second) else if (!hor && it.first > num) (2 * num - it.first) to it.second else it }.distinct().size
    }

    fun List<Int>.asLine(): String = (0..last()).joinToString("") { if (it in this) "||" else "  " }


    fun part2(input: List<String>) {
        val seperator = input.indexOf("")
        var (a, b) = input.partition { input.indexOf(it) < seperator }.let { it.first.map { l -> l.split(",").map(String::toInt).let { p -> p[0] to p[1] } } to it.second.drop(1).map { l -> l.split("along ")[1] } }

        b.map { f -> f.split("=").let { (it[0] == "y") to (it[1].toInt()) } }.forEach { (hor, num) ->
            a = a.map { if (hor && it.second > num) it.first to (2 * num - it.second) else if (!hor && it.first > num) (2 * num - it.first) to it.second else it }.distinct()
        }

        println(a.sortedWith { a, b -> a.first - b.first }.groupBy { it.second }.entries.map { (k, v) -> k to v.map { it.first }.sorted() }.sortedBy { it.first }.joinToString("\n") { (_, v) -> v.asLine() })
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    check(part1(testInput) == 17)
    part2(testInput)

    val input = readInput("day$day\\Day$day")
    println("\npart1: ${part1(input)}\n")
    part2(input)
}
