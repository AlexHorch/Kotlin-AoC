package y2021.day17

import kotlin.math.sign

fun main() {
    fun solve(xMin: Int, xMax: Int, yMin: Int, yMax: Int): Int {
        val checker = { x: Int, y: Int ->
            var (a, b) = 0 to 0
            var (vh, vv) = x to y
            var valid = false
            while (a <= xMax && b >= yMin) {
                if (a >= xMin && b <= yMax) {
                    valid = true
                    break
                }
                a += vh.also { vh -= vh.sign }
                b += vv--
            }
            valid
        }

        return (1..30).flatMap { x -> (yMin until -yMin).map { y -> checker(x, y) } }.count { it }
    }

    println(solve(20, 30, -10, -5))
    println(solve(119, 176, -141, -84))
}
