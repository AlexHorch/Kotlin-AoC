package y2021.day10

import y2021.readInput

const val day = "10"
fun main() {
    val opening = mapOf(')' to '(', ']' to '[', '}' to '{', '>' to '<')
    val closing = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
    val points1 = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    val points2 = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)
    fun foo(acc: Pair<List<Char>, Char?>, c: Char): Pair<List<Char>, Char?> = if (acc.second != null) acc else if (c in "([{<") acc.first + c to null else if (acc.first.last() == opening[c]) acc.first.dropLast(1) to null else acc.first to c
    fun part1(input: List<String>): Int {
        return input.mapNotNull { s -> s.fold(listOf<Char>() to null, ::foo).second }.sumOf { points1[it]!! }
    }

    fun nonCorrupt(s: String): List<Char>? = s.fold(listOf<Char>() to null, ::foo).let { if (it.second != null) null else it.first }

    fun score2(acc: Long, points: Int) = acc * 5 + points

    fun openedToPoints(list: List<Char>) = list.reversed().map { points2[closing[it]!!]!! }

    check(nonCorrupt("{([(<{}[<>[]}>{[]{[(<()>") == null)
    check(nonCorrupt("<{([{{}}[<[[[<>{}]]]>[]]") == "<{([".map { it })

    check(openedToPoints(listOf('(', '[', '{', '<')) == listOf(4, 3, 2, 1))
    check(listOf(2, 1, 3, 4).fold(0L, ::score2) == 294L)

    check(openedToPoints("<{([".map { it }).fold(0L, ::score2) == 294L)

    fun part2(input: List<String>): Long {
        return input.mapNotNull(::nonCorrupt).map { openedToPoints(it).fold(0L, ::score2) }.sorted().let { it[it.size / 2] }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    check(part1(testInput) == 26397)
    with(part2(testInput)) { println(this); check(this == 288957L) }

    val input = readInput("day$day\\Day$day")
    println(part1(input))
    println(part2(input))
}
