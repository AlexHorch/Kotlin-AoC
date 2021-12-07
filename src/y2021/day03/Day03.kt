package y2021.day03

import y2021.readInput

const val day = "03"
fun main() {
    fun part1(input: List<String>): Int {
        val indices = input.first().indices
        val foo = indices.map { n -> input.groupBy { it[n] } }
        val (gamma, epsilon) = indices.joinToString("") { n -> foo[n].let { if ((it['0']?.size ?: 0) > (it['1']?.size ?: 0)) "0" else "1" } }
            .let { s -> listOf(s, s.map { if (it == '0') '1' else '0' }.joinToString("")) }
            .map { Integer.parseInt(it, 2) }

        return gamma * epsilon
    }

    fun part2(input: List<String>): Int = rec(0, input, input).let { it.first * it.second }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("day$day\\Day$day")
    println("part 1: ${part1(input)}")
    println("part 2: ${part2(input)}")
}

fun rec(pos: Int, oxygen: List<String>, scrubber: List<String>): Pair<Int, Int> = if (oxygen.size == 1 && scrubber.size == 1) Integer.parseInt(oxygen.first(), 2) to Integer.parseInt(scrubber.first(), 2) else
    rec(pos + 1, if (oxygen.size == 1) oxygen else oxygen.groupBy { it[pos] }.let { if ((it['0']?.size ?: 0) > (it['1']?.size ?: 0)) it['0']!! else it['1']!! }, if (scrubber.size == 1) scrubber else scrubber.groupBy { it[pos] }.let { if ((it['1']?.size ?: 0) < (it['0']?.size ?: 0)) it['1']!! else it['0']!! })
