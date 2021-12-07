package y2021.day06

import y2021.readInput

const val day = "06"
fun main() {
    fun part1(input: List<String>): Int {
        var fish = input.first().split(",").map(String::toInt).groupBy { it }.let { m -> (0..8).map { m[it]?.size ?: 0 } }
        repeat(80) { fish = fish.drop(1).take(6) + (fish[7] + fish[0]) + fish[8] + fish[0] }
        return fish.sum()
    }

    fun part2(input: List<String>): Long {
        var fish = input.first().split(",").map(String::toInt).groupBy { it }.let { m -> (0..8).map { (m[it]?.size ?: 0).toLong() } }
        repeat(256) { fish = fish.drop(1).take(6) + (fish[7] + fish[0]) + fish[8] + fish[0] }
        return fish.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    check(part1(testInput) == 5934)
    check(part2(testInput) == 26984457539)

    val input = readInput("day$day\\Day$day")
    println(part1(input))
    println(part2(input))
}
