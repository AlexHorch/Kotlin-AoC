package y2021.day09

import y2021.readInput


const val day = "09"
fun main() {
    fun part1(input: List<String>): Int {
        val grid = input.map { line -> line.map { it.toString().toInt() } }
        fun adjacent(x: Int, y: Int) = listOf((if (x > 0) x - 1 to y else null), (if (x < grid.lastIndex) x + 1 to y else null), (if (y > 0) x to y - 1 else null), (if (y < grid.first().lastIndex) x to y + 1 else null)).filterNotNull()

        return (0..grid.lastIndex).sumOf { x -> (0..grid.first().lastIndex).sumOf { y -> if (adjacent(x, y).all { (a, b) -> grid[a][b] > grid[x][y] }) grid[x][y] + 1 else 0 } }
    }

    fun part2(input: List<String>): Int {
        val grid = input.map { line -> line.map { it.toString().toInt() } }
        val unfilled = grid.flatMapIndexed { y, line -> line.mapIndexed { x, i -> y to x to i } }.filter { it.second != 9 }.toMap().toMutableMap()
        fun adjacent(x: Int, y: Int) = unfilled.entries.filter { (k, _) -> k in setOf(y + 1 to x, y - 1 to x, y to x + 1, y to x - 1) }
        val basins = mutableListOf<Int>()
        while (unfilled.isNotEmpty()) {
            val start = unfilled.entries.random().also { (k, _) -> unfilled.remove(k) }
            val basin = mutableListOf(start)
            var adjacent = basin.flatMap { (k, _) -> adjacent(k.first, k.second) }
            while (adjacent.isNotEmpty()) {
                basin.addAll(adjacent)
                adjacent.forEach { (k,_) -> unfilled.remove(k) }
                adjacent = basin.flatMap { (k, _) -> adjacent(k.first, k.second) }
            }
            basins.add(basin.size)
        }
        return basins.sorted().takeLast(3).fold(1, Int::times)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    val input = readInput("day$day\\Day$day")
    check(part1(testInput) == 15)
    println("part 1: ${part1(input)}")

    check(part2(testInput).also(::println) == 1134)
    println("part 2: ${part2(input)}")
}
