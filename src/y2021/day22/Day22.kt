package y2021.day22

import y2021.readInput

const val day = "22"
fun main() {

    fun part1(input: List<String>): Long {
        return input.mapNotNull { Cube.fromString(it).limit(50) }.fold(listOf<Cube>()) { acc, next -> acc.mapNotNull { next.intersect(it) }.let { if (next.on) acc + next else acc } }.sumOf { it.value() }
    }

    fun part2(input: List<String>): Long {
        return input.map { Cube.fromString(it) }.fold(listOf<Cube>()) { acc, next -> acc.mapNotNull { next.intersect(it) }.let { if (next.on) acc + next else acc } }.sumOf { it.value() }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    val input = readInput("day$day\\Day$day")
    check(part1(testInput).also(::println) == 590784L)
    println("part1: ${part1(input)}")
    check(part2(testInput) == 2758514936282235L)
    println("part2: ${part2(input)}")
}

fun Int.max(other: Int): Int = if (this > other) this else other
fun Int.min(other: Int): Int = if (this < other) this else other

class Cube(private val xMin: Int, private val xMax: Int, private val yMin: Int, private val yMax: Int, private val zMin: Int, private val zMax: Int, internal val on: Boolean) {
    companion object {
        fun fromString(input: String): Cube = input.split(" x=", ",y=", ",z=", "..").let { (it.first() == "on") to it.drop(1).map(String::toInt) }.let { Cube(it.second[0], it.second[1], it.second[2], it.second[3], it.second[4], it.second[5], it.first) }
    }

    fun value(): Long = (xMax - xMin + 1).toLong() * (yMax - yMin + 1).toLong() * (zMax - zMin + 1).toLong()
    fun intersect(other: Cube): Cube? {
        val (x1, x2) = xMin.max(other.xMin) to xMax.min(other.xMax)
        val (y1, y2) = yMin.max(other.yMin) to yMax.min(other.yMax)
        val (z1, z2) = zMin.max(other.zMin) to zMax.min(other.zMax)
        return if (x1 > x2 || y1 > y2 || z1 > z2) null else Cube(x1, x2, y1, y2, z1, z2, !other.on)
    }

    fun limit(size: Int): Cube? {
        val (x1, x2) = xMin.max(-size) to xMax.min(size)
        val (y1, y2) = yMin.max(-size) to yMax.min(size)
        val (z1, z2) = zMin.max(-size) to zMax.min(size)
        return if (x1 > x2 || y1 > y2 || z1 > z2) null else Cube(x1, x2, y1, y2, z1, z2, on)
    }
}
