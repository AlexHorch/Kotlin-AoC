package y2021.day15

import y2021.readInputAsDigitGrid

const val day = "15"
fun main() {

    fun Int.foo() = if (this > 9) this - 9 else this

    fun plotCourse(cave: List<MutableList<Int>>): Int = cave.indices.forEach { y ->
        cave.first().indices.forEach { x ->
            cave[y][x] = when {
                y == 0 && x == 0 -> 0
                y == 0 -> cave[y][x] + cave[y][x - 1]
                x == 0 -> cave[y][x] + cave[y - 1][x]
                else -> cave[y][x] + cave[y][x - 1].coerceAtMost(cave[y - 1][x])
            }
        }
    }.let { cave.last().last() }

    fun List<List<Int>>.mod(n: Int): List<List<Int>> = map { line -> line.map { (it + n).foo() } }
    fun List<List<Int>>.joinHor(other: List<List<Int>>): List<MutableList<Int>> = zip(other).map { (a, b) -> (a + b).toMutableList() }

    fun blowUp(input: List<List<Int>>): List<MutableList<Int>> = (0..4).map { y ->
        (0..4).map { x ->
            input.mod(y + x)
        }.fold<List<List<Int>>, List<MutableList<Int>>>(emptyList(), List<List<Int>>::joinHor)
    }.fold(emptyList()) { acc, list -> acc + list }


    fun part1(input: List<List<Int>>): Int {
        return plotCourse(input.map { line -> line.map { it }.toMutableList() })
    }

    fun part2(input: List<List<Int>>): Int {
        return plotCourse(blowUp(input))
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsDigitGrid("day$day\\Day${day}_test")
    val input = readInputAsDigitGrid("day$day\\Day$day")

    check(part1(testInput) == 40)
    println("part 1: ${part1(input)}")

    check(part2(testInput).also(::println) == 315)

    println("part 2: ${part2(input)}")
}
