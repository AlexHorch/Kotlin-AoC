package y2021.day20

import y2021.readInput

const val day = "20"
fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    check(part1(testInput).also(::println) == 79)
//    check(part2(testInput) == 1)

    val input = readInput("day$day\\Day$day")
    println(part1(input))
//    println(part2(input))
}
