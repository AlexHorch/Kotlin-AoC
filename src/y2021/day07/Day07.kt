package y2021.day07

import y2021.readInputAsIntegers
import kotlin.math.abs

const val day = "07"
fun main() {
    fun part1(input: List<Int>): Int {
        val min = input.minOf { it }
        val max = input.maxOf { it }
        return (min..max).minOf { x -> input.sumOf { abs(x - it) } }
    }

    val fuelCosts = mutableMapOf(0 to 0)
    fun MutableMap<Int, Int>.cost(n: Int): Int = getOrPut(n) { n + cost(n - 1) }

    fun part2(input: List<Int>): Int {
        val min = input.minOf { it }
        val max = input.maxOf { it }
        return (min..max).minOf { x -> input.sumOf { fuelCosts.cost(abs(x - it)) } }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsIntegers("day$day\\Day${day}_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInputAsIntegers("day$day\\Day$day")
    println(part1(input))
    println(part2(input))
}

