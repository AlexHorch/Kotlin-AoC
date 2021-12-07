package y2021.day04

import y2021.readInput

const val day = "04"
fun main() {
    fun part1(input: List<String>): Int {
        val boards = input.drop(1).windowed(6, 6, false).map { Board(it.drop(1)) }
        val sequence = input.first().split(",").map(String::toInt)
        var idx = -1;
        while (boards.none(Board::bingo)) {
            idx++
            boards.forEach { it.mark(sequence[idx]) }
        }
        val unmarked = boards.find(Board::bingo)!!.unmarked()
        return unmarked * sequence[idx]
    }

    fun part2(input: List<String>): Int {
        var boards = input.drop(1).windowed(6, 6, false).map { Board(it.drop(1)) }
        val calls = input.first().split(",").map(String::toInt).iterator()
        var call = 0;

        while (boards.filter { !it.bingo() }.also { boards = it }.size > 1) {
            call = calls.next()
            boards.forEach { it.mark(call) }
        }
        val looser = boards.first()
        while (!looser.bingo()) {
            looser.mark(calls.next().also { call = it })
        }
        return looser.unmarked() * call
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("day$day\\Day$day")
    println(part1(input))
    println(part2(input))
}

class Board(input: List<String>) {
    private val values: List<List<Int>> = input.map { line -> line.split(" ").filter(String::isNotBlank).map(String::toInt) }
    private val marked: List<MutableList<Boolean>> = List(5) { MutableList(5) { false } }
    private var bingo = false;

    fun mark(n: Int) = (0..4).forEach { y -> (0..4).forEach { x -> marked[y][x] = marked[y][x] || values[y][x] == n } }
    fun bingo(): Boolean = (bingo || marked.any { l -> l.all { it } } || (0..4).any { marked.all { l -> l[it] } }).also { bingo = it }
    fun unmarked() = (0..4).flatMap { y -> (0..4).map { x -> y to x } }.filter { (y, x) -> !marked[y][x] }.sumOf { (y, x) -> values[y][x] }
}
