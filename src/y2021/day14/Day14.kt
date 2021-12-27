package y2021.day14

import y2021.readInput

const val day = "14"
fun main() {

    fun String.toPairAmounts(): List<Pair<String, Long>> = windowed(2, 1, false).groupBy { it }.map { entry -> entry.key to entry.value.size.toLong() }.sortedBy { it.first }
    fun pairAmountsToCharAmounts(pairAmounts: List<Pair<String, Long>>): Map<Char, Long> {
        val x = mutableMapOf<Char, Long>()
        pairAmounts.forEach { (k, v) -> k.forEach { x[it] = x[it]?.plus(v) ?: v } }
        return x.entries.associate { (k, v) -> k to (v + 1) / 2L }
    }

    fun maxMinusMin(charMap: Map<Char, Long>): Long = charMap.values.sorted().let { it.last() - it.first() }

    fun rulesToInsertionMapper(rules: List<String>): Map<String, List<String>> = rules.map { rule -> rule.split(" -> ").let { it[0] to listOf(it[0][0] + it[1], it[1] + it[0][1]) } }.toMap()

    fun getStepper(mapper: Map<String, List<String>>): (String) -> List<String> = { s -> mapper.getOrDefault(s, listOf(s)) }


    fun List<Pair<String, Long>>.step(stepper: (String) -> List<String>, n: Int): List<Pair<String, Long>> = flatMap { (f, s) -> stepper(f).map { it to s } }.groupBy { it.first }.map { (k, v) -> k to v.sumOf { it.second } }.sortedBy { it.first }
        .let { if (n > 0) it.step(stepper, n - 1) else it }

    fun reactNTimes(input: List<String>, n: Int): Long = maxMinusMin(pairAmountsToCharAmounts(input.first().toPairAmounts().let { if (n > 0) it.step(getStepper(rulesToInsertionMapper(input.drop(2))), n - 1) else it }))

    fun part1(input: List<String>): Long = reactNTimes(input, 10)

    fun part2(input: List<String>): Long = reactNTimes(input, 40)

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    val input = readInput("day$day\\Day$day")
    check(part1(testInput).also(::println) == 1588L)
    println("solution p1: ${part1(input)}")

    check(part2(testInput).also(::println) == 2188189693529L)
    println("solution p2: ${part2(input)}")
}
