package y2021.day01

import y2021.readInput

fun main() {
    fun part1(input: List<String>): Int = input.map { it.toInt() }.windowed(2, 1, false).sumOf { (if (it[0] < it[1]) 1 else 0).toInt() }

    fun part2(input: List<String>): Int = input.map { it.toInt() }.windowed( 4, 1, false).sumOf { (if (it[0] < it[3]) 1 else 0).toInt() }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day01\\Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("day01\\Day01")
    println(part1(input))
    println(part2(input))
}
