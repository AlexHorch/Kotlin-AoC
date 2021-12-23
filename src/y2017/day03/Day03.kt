package y2017.day03

import y2017.readInput

const val day = "03"
fun main() {
    fun part1(input: Int): Int {
        var odd = 1
        while (odd * odd < input) odd += 2

        val distance = odd - 1

        val dr = odd * odd
        val dl = dr - odd
        val ul = dl - odd
        val ur = ul - odd

        return when {
            input in setOf(dr, dl, ul, ur) -> distance
            input > dl -> dr - input
            input > ul -> dl - input
            input > ur -> ul - input
            else -> ur - input
        }
    }

    fun part2(input: Int): Int {
        fun count(start: Int, step: Int) = sequence {
            var c = start
            while (true) yield(c).also { c += step }
        }

        fun sumSpiral() = sequence<Int> {
            val a = mutableMapOf(0 to 0 to 1)
            var (i, j) = 0 to 0
            val sn: (Int, Int) -> Int = { x, y -> (x - 1..x + 1).sumOf { i -> (y - 1..y + 1).sumOf { j -> a.getOrDefault(i to j, 0) } } }
            count(1, 2).forEach { s ->
                (0 until s).forEach { yield(sn(++i, j).also { a[i to j] = it }) }
                (0 until s).forEach { yield(sn(i, --j).also { a[i to j] = it }) }
                (0..s).forEach { yield(sn(--i, j).also { a[i to j] = it }) }
                (0..s).forEach { yield(sn(i, ++j).also { a[i to j] = it }) }
            }
        }
        return sumSpiral().first { it > input }
    }

    // test if implementation meets criteria from the description, like:

//    check(part1(1) == 0)
//    check(part1(12) == 3)
//    check(part1(23) == 2)
    check(part1(1024) == 31)
//    check(part1(312051) == 430)

//    check(part2(testInput) == 1)

    val input = 312051
    println(part1(input))
    println(part2(input))
}
