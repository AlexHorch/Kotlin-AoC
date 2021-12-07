package y2021.day02

import y2021.readInput

fun main() {
    fun part1(input: List<String>): Int = input.map { it.split(" ") }.map {
        when (it[0]) {
            "forward" -> 0 to it[1].toInt()
            "down" -> it[1].toInt() to 0
            else -> -it[1].toInt() to 0
        }
    }.fold(0 to 0) { acc, pair -> acc.first + pair.first to acc.second + pair.second }.let { it.first * it.second }

    fun part2(input: List<String>): Int = input.map{it.split(" ").let { it[0] to it[1].toInt() }}.fold(Triple(0,0,0)) { acc, pair ->
        when (pair.first) {
            "up" -> acc.copy(third = acc.third - pair.second)
            "down" -> acc.copy(third = acc.third + pair.second)
            else -> acc.copy(first = acc.first + pair.second, second = acc.second + pair.second * acc.third)
        }
    }.let { it.first * it.second }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day02\\Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("day02\\Day02")
    println(part1(input))
    println(part2(input))
}
