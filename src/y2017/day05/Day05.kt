package y2017.day05

import y2017.readInput

const val day = "05"
fun main() {
    fun part1(input: List<String>): Int {
        val offsets = input.map(String::toInt).toMutableList()
        var counter = 0
        var i = 0

        while (i in offsets.indices) offsets[i]++.also { i += it }.also { counter++ }

        return counter
    }

    fun part2(input: List<String>): Int {
        val offsets = input.map(String::toInt).toMutableList()
        var counter = 0
        var i = 0

        while (i in offsets.indices) {
            val tmp = offsets[i]
            offsets[i] += if (tmp < 3) 1 else -1
            counter++
            i += tmp
        }

        return counter
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 10)

    val input = readInput("day$day\\Day$day")
    println(part1(input))
    println(part2(input))
}
