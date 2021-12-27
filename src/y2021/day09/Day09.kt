package y2021.day09

import y2021.readInput


const val day = "09"
fun main() {
    fun part1(input: List<String>): Int {
        val grid = input.map { line -> line.map { it.toString().toInt() } }
        fun adjacent(x: Int, y: Int) = listOfNotNull(
            (if (x > 0) x - 1 to y else null),
            (if (x < grid.lastIndex) x + 1 to y else null),
            (if (y > 0) x to y - 1 else null),
            (if (y < grid.first().lastIndex) x to y + 1 else null)
        )

        return (0..grid.lastIndex).sumOf { x -> (0..grid.first().lastIndex).sumOf { y -> if (adjacent(x, y).all { (a, b) -> grid[a][b] > grid[x][y] }) grid[x][y] + 1 else 0 } }
    }

    fun part2(input: List<String>): Int {
        val grid = input.map { line -> line.map { it.toString().toInt() } }
        val unfilled = grid.flatMapIndexed { y, line -> line.mapIndexed { x, i -> y to x to i } }.filter { it.second != 9 }.map{it.first}.toMutableSet()
        fun Pair<Int, Int>.adj() = setOf(first + 1 to second, first - 1 to second, first to second + 1, first to second - 1)
        fun adjacent(key:Pair<Int, Int>) = key.adj().filter { it in unfilled }.map { k -> unfilled.remove(k); k }
        val basins = mutableListOf<Int>()
        while (unfilled.isNotEmpty()) {
            val start = unfilled.random()
            val basin = mutableSetOf(start)
            var adjacent = basin.flatMap (::adjacent)
            while (adjacent.isNotEmpty()) {
                basin.addAll(adjacent)
                adjacent = basin.flatMap (::adjacent)
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
