package y2021.day11

import y2021.readInput

const val day = "11"

class Counter {
    var count: Int = 0

    operator fun inc(): Counter {
        count++
        return this
    }

    fun reset() {
        count = 0
    }

    infix fun eq(num: Int): Boolean = count == num
}

fun main() {
    fun pairs(y: Int, x: Int) = (-1..1).map(y::plus).filter { it in 0..9 }.flatMap { a -> (-1..1).map(x::plus).filter { it in 0..9 }.map { b -> a to b } }
    fun List<String>.toMutableNumberGrid() = map { line -> line.trim().split("").filter { it.isNotBlank() && it.isNotEmpty() }.map(String::toInt).toMutableList() }

    fun foo(octopi: List<MutableList<Int>>, flashes: Counter) = { y: Int, x: Int ->
        octopi[y][x] = 0
        flashes.count++
        pairs(y, x).forEach { (a, b) ->
            if (octopi[a][b] != 0) octopi[a][b]++
        }
    }

    fun gameLoop(octopi: List<MutableList<Int>>, flash: (Int, Int) -> Unit) {
        octopi.forEachIndexed { y, list ->
            list.indices.forEach { x ->
                octopi[y][x]++
            }
        }
        while (octopi.flatten().any { it > 9 }) {
            octopi.forEachIndexed { y, list ->
                list.forEachIndexed { x, i ->
                    if (i > 9) flash(y, x)
                }
            }
        }
    }

    fun part1(input: List<String>, times: Int = 100): Int {
        var flashes = Counter()
        val octopi = input.toMutableNumberGrid()

        val flash = foo(octopi, flashes)

        repeat(times) { gameLoop(octopi, flash) }

        return flashes.count
    }

    fun part2(input: List<String>): Int {
        val octopi = input.toMutableNumberGrid()
        var flashes = Counter()
        var turn = 0

        val flash = foo(octopi, flashes)

        while (!(flashes eq 100)) {
            flashes.reset()
            turn++
            gameLoop(octopi, flash)
        }
        return turn
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    val input = readInput("day$day\\Day$day")

    check(part1(testInput) == 1656)
    println("part 1: ${part1(input)}")

    check(part2(testInput).also(::println) == 195)

    println("part 2: ${part2(input)}")
}
