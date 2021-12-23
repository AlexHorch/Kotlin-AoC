package y2021.day08

import y2021.readInput

const val day = "08"
fun main() {
    fun part1(input: List<String>): Int {
        val lengths = listOf(2, 3, 4, 7)
        return input.flatMap { it.split("| ")[1].split(" ").filter { it.length in lengths } }.size
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { line ->
            val (encoding, display) = line.split(" | ").let { decode(it[0]) to it[1].split(" ").map { s -> s.toSet() } }
            display.map { x -> encoding.entries.find { it.value == x }!!.key }.let { it[0] * 1000 + it[1] * 100 + it[2] * 10 + it[3] }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("day$day\\Day$day")
    println(part1(input))
    println(part2(input))
}

fun decode(encoding: String): Map<Int, Set<Char>> {
    val codes = encoding.split(" ").sortedBy { it.length }.map { it.toSet() }
    val one = codes[0]
    val seven = codes[1]
    val four = codes[2]
    val eight = codes[9]
    val (five, three, two) = codes.sort(5, { it.intersect(four - one).size == 2 }, { it.intersect(one).size == 2 })

    val (six, nine, zero) = codes.sort(6, { !it.contains((one - five).first()) }, { it.intersect(four).size == 4 })

    return mapOf(1 to one, 2 to two, 3 to three, 4 to four, 5 to five, 6 to six, 7 to seven, 8 to eight, 9 to nine, 0 to zero)
}

fun List<Set<Char>>.sort(size: Int, p1: (Set<Char>) -> Boolean, p2: (Set<Char>) -> Boolean): List<Set<Char>> = filter { it.size == size }.partition(p1).let {
    val a = it.first.first()
    val (b, c) = it.second.partition(p2).let { p -> p.first.first() to p.second.first() }
    listOf(a, b, c)
}
