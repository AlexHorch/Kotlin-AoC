package y2018.day05

import y2018.readInput


const val day = "05"
fun main() {
    fun part1(input: List<String>): Int {
        return rec(input.first())
    }

    fun part2(input: List<String>): Int {
        return x.map { input.first().replace("$it", "", true) }.minOf(::rec)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    check(part1(testInput) == 10)
    check(part2(testInput) == 4)

    val input = readInput("day$day\\Day$day")
    println(part1(input))
    println(part2(input))
}

val x = (1..26).map { it + 64 }.map(Int::toChar)
val y = x.flatMap { listOf("$it${it + 32}", "${it + 32}$it") }

fun rec(line: String): Int = y.fold(line) { acc, pair -> acc.replace(pair, "") }.let { if (it.length < line.length) rec(it) else line.length }

