package y2017.day06

import y2017.readInput
import y2017.readInputAsIntegers

const val day = "06"
fun main() {
    fun foo(max: Int, size: Int, index: Int, idx: Int) = (if (idx > index) (idx - index) else (size - index + idx)) <= (max % size)

    check(foo(7, 4, 2, 0))
    check(foo(7, 4, 2, 1))
    check(!foo(7, 4, 2, 2))
    check(foo(7, 4, 2, 3))

    fun List<Int>.balance(): List<Int> {
        val (max, index) = maxOf { it }.let { it to indexOf(it) }
        return mapIndexed { idx, it -> (if (idx != index) it else 0) + (max / size) + (if (foo(max, size, index, idx)) 1 else 0) }
    }

    fun part1(input: List<Int>): Int {
        val seen = mutableSetOf(input)
        var current = input.balance()
        while (current !in seen) {
            seen.add(current)
            current = current.balance()
        }

        return seen.size
    }

    fun part2(input: List<Int>): Int {
        val seen = mutableListOf(input)
        var current = input.balance()
        while (current !in seen) {
            seen.add(current)
            current = current.balance()
        }

        return seen.size - seen.indexOf(current)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test").first().split(" ").map(String::toInt)
    check(part1(testInput) == 5)
    check(part2(testInput) == 4)

    val input = readInput("day$day\\Day$day").first().split(" ").map(String::toInt)
    println(part1(input))
    println(part2(input))
}
