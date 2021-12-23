package y2021.day21


fun main() {
    fun part1(input: Pair<Int, Int>): Int {
        val d100 = Dice()
        val player1 = Player(input.first, d100)
        val player2 = Player(input.second, d100)

        while (true) {
            if (player1.play() >= 1000) {
                return player2.score * d100.rolled
            }
            if (player2.play() >= 1000) {
                return player1.score * d100.rolled
            }
        }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val test = 4 to 8
    check(part1(test).also(::println) == 739785)
//    check(part2(testInput) == 1)

    val actual = 5 to 9
    println(part1(actual))
//    println(part2(input))
}

class Dice {
    var rolled = 0
    var next = 1

    fun roll(): Int {
        rolled++
        return next.also { if (next == 100) next = 1 else next += 1 }
    }
}

class Player(var position: Int, val dice: Dice) {
    var score = 0

    fun play(): Int {
        val move = (dice.roll() + dice.roll() + dice.roll()) % 10
        val next = (position + move).let { if(it > 10) it - 10 else it }
        score += next
        position = next
        return score
    }
}
