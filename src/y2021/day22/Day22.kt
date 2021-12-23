package y2021.day22

import y2021.readInput

const val day = "22"
fun main() {

    fun cuber(lB: Int, uB: Int): (IntRange, IntRange, IntRange) -> List<Triple<Int, Int, Int>> {
        fun cubes(xs: IntRange, ys: IntRange, zs: IntRange) = xs.filter { x -> x in (lB..uB) }.flatMap { x ->
            ys.filter { y -> y in (lB..uB) }.flatMap { y ->
                zs.filter { z -> z in (lB..uB) }.map { z ->
                    Triple(x, y, z)
                }
            }
        }
        return ::cubes
    }

    val c50 = cuber(-50, 50)
    fun String.toRange(): IntRange = drop(2).split("..").map(String::toInt).let {
        when {
            it[0] < -50 && it[1] > -50
        }
        it[0]..it[1]
    }

    fun part1(input: List<String>): Int {
        return input
            .map { line -> line.split(" x=", ",y=", ",z=", "..").let { it.first() to it.drop(1).map(String::toInt) } }.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    check(part1(testInput) == 590784)
//    check(part2(testInput) == 1)

    val input = readInput("day$day\\Day$day")
    println(part1(input))
//    println(part2(input))
}


