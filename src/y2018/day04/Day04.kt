package y2018.day04

import y2018.readInput
import java.time.LocalDateTime.parse
import java.time.format.DateTimeFormatter

val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
const val day = "04"
fun main() {
    fun part1(input: List<String>): Int {
        input.map { l -> l.split("] ").let { parse(it[0].drop(1), formatter) to it[1] } }.sortedBy { it.first }
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    val foo = parse("1518-11-05 00:55", formatter)
    println(foo)

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    check(part1(testInput) == 10)
    check(part2(testInput) == 4)

    val input = readInput("day$day\\Day$day")
    println(part1(input))
    println(part2(input))

}

