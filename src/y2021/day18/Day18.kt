package y2021.day18

import y2021.readInput

const val day = "18"
fun main() {
//    fun part1(input: List<String>): Int {
//        return input.size
//    }
//
//    fun part2(input: List<String>): Int {
//        return input.size
//    }

    println("[1,2]".drop(1).parse())
    println("[[1,2],3]".drop(1).parse())


    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("day$day\\Day${day}_test")
//    check(part1(testInput) == 1)
//    check(part2(testInput) == 1)

//    val input = readInput("day$day\\Day$day")
//    println(part1(input))
//    println(part2(input))
}

interface SnailNode {
    val magnitude: Int
}

class SnailPair(var left: SnailNode, var right: SnailNode) : SnailNode {
    override val magnitude: Int
        get() = 3 * left.magnitude + 2 * right.magnitude
}

class SnailNumber(c: Char) : SnailNode {
    val value = c.toString().toInt()
    override val magnitude: Int
        get() = value
}

fun String.parse(): Pair<SnailNode, String> = (if (this[0] == '[') drop(1).parse() else (SnailNumber(this[0]) to drop(2))).let { (left, tail) ->
    (if (tail[0] == '[') tail.drop(1).parse() else (SnailNumber(tail[0]) to tail.drop(1))).let { (right, rest) -> SnailPair(left, right) to rest.drop(1) }
}
