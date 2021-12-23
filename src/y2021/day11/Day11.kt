package y2021.day11

import y2021.readInput

const val day = "11"
fun main() {
    fun part1(input: List<String>): Int {
        var flashes = 0
        val octopi = input.map { line -> line.split("").map(String::toInt).toMutableList() }
        fun flash(y: Int, x: Int) {
            octopi[y][x] = 0
            flashes++
            (-1..+1).forEach { a ->
                (-1..+1).forEach { b ->
                    if (octopi[y + a][b + x] != 0) {
                        octopi[y + a][b + x]++
                    }
                }
            }
        }
        (1..100).forEach {
            octopi.forEachIndexed { y, list ->
                list.indices.forEach { x ->
                    octopi[y][x]++
                }
            }
            while (octopi.flatten().any { it > 8 }) {
                octopi.forEachIndexed { y, list ->
                    list.forEachIndexed { x, i ->
                        if (i > 8) flash(y, x)
                    }
                }
            }
        }

        return flashes
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    check(part1(testInput) == 1656)
//    check(part2(testInput) == 1)

    val input = readInput("day$day\\Day$day")
    println(part1(input))
//    println(part2(input))
}
