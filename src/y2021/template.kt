package y2021

const val day = "00"
fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    val input = readInput("day$day\\Day$day")

    check(part1(testInput) == 1656)
    println("part 1: ${part1(input)}")

    check(part2(testInput).also(::println) == 195)

    println("part 2: ${part2(input)}")
}
