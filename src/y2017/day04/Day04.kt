package y2017.day04

import y2017.readInput

const val day = "04"
fun main() {
    fun valid(input: String) = input.split(" ").let { it.size == it.toSet().size }
    fun valid2(input: String): Boolean = input.split(" ").map { w -> w.map { it }.sorted() }.let { it.size == it.distinct().size }


    fun part1(input: List<String>): Int {
        return input.count(::valid)
    }

    fun part2(input: List<String>): Int {
        return input.count(::valid2)
    }

    // test if implementation meets criteria from the description, like:
    check(valid("aa bb cc dd ee"))
    check(!valid("aa bb cc dd aa"))
    check(valid("aa bb cc dd aaa"))

    check(valid2("abcde fghij"))
    check(!valid2("abcde xyz ecdab"))
    check(valid2("a ab abc abd abf abj"))
    check(valid2("iiii oiii ooii oooi oooo"))
    check(!valid2("oiii ioii iioi iiio"))

    val input = readInput("day$day\\Day$day")
    println(part1(input))
    println(part2(input))
}


