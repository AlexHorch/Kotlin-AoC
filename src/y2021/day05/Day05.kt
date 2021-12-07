package y2021.day05

import y2021.readInput

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

const val day = "05"
fun main() {
    fun part1(input: List<String>): Int = input.map { Line(it) }.filter { !it.diagonal() }.flatMap(Line::covers).groupBy { it }.values.filter { it.size > 1 }.size

    fun part2(input: List<String>): Int = input.map { Line(it) }.flatMap(Line::covers).groupBy { it }.values.filter { it.size > 1 }.size

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("day$day\\Day$day")
    println(part1(input))
    println(part2(input))
}

class Line(input: String) {
    private val x1: Int
    private val x2: Int
    private val y1: Int
    private val y2: Int

    init {
        input.split(" -> ").let { ends ->
            ends[0].split(",").map(String::toInt).let { x1 = it[0];y1 = it[1] }
            ends[1].split(",").map(String::toInt).let { x2 = it[0];y2 = it[1] }
        }
    }

    fun diagonal() = x1 != x2 && y1 != y2
    fun covers(): List<Pair<Int, Int>> = if (diagonal()) {
        val sx = if (x1 > x2) -1 else 1
        val sy = if (y1 > y2) -1 else 1
        (0..abs(x1 - x2)).map { off -> y1 + off * sy to x1 + off * sx }
    } else (min(x1, x2)..max(x1, x2)).flatMap { x -> (min(y1, y2)..max(y1, y2)).map { y -> y to x } }
}

