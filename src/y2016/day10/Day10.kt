package y2016.day10

import y2016.readInput

const val day = "10"
fun main() {
    fun part1(input: List<String>, a: Int, b: Int): Int {
        val (valueInputs, botInputs) = input.partition { it.startsWith("value") }
        val bots = botInputs.map { it.split(" ")[1].toInt() to Bot(it) }.toMap()
        valueInputs.map { it.split(" ") }.forEach { bots[it[5].toInt()]!!.recieve(it[1].toInt(), bots) }
        return bots.entries.find { it.value.received(a, b) }!!.key
    }

    fun part2(input: List<String>): Int {
        val (valueInputs, botInputs) = input.partition { it.startsWith("value") }
        val bots = botInputs.map { it.split(" ")[1].toInt() to Bot(it) }.toMap()
        val outputs = mutableMapOf<Int, Int>()
        valueInputs.map { it.split(" ") }.forEach { bots[it[5].toInt()]!!.recieve(it[1].toInt(), bots, outputs) }
        return (0..2).map { outputs[it]!! }.fold(1) { acc, i -> acc * i }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    check(part1(testInput, 5, 2) == 2)
//    check(part2(testInput) == 1)

    val input = readInput("day$day\\Day$day")
    println(part1(input, 61, 17))
    println(part2(input))
}

class Bot(input: String) {
    val recieved = mutableListOf<Int>()
    val receivers: Pair<String, String> = input.split(" ").let { "${it[5]} ${it[6]}" to "${it[10]} ${it[11]}" }
    fun recieve(value: Int, bots: Map<Int, Bot>, outputs: MutableMap<Int, Int> = mutableMapOf()) {
        recieved.add(value)
        if (recieved.size > 1) {
            val (low, high) = recieved.minOf { it } to recieved.maxOf { it }
            if (receivers.first.startsWith("bot")) {
                bots[receivers.first.split(" ")[1].toInt()]!!.recieve(low, bots)
            } else {
                outputs[receivers.first.split(" ")[1].toInt()] = low
            }
            if (receivers.second.startsWith("bot")) {
                bots[receivers.second.split(" ")[1].toInt()]!!.recieve(high, bots)
            } else {
                outputs[receivers.second.split(" ")[1].toInt()] = high
            }
        }
    }

    fun received(a: Int, b: Int) = recieved.intersect(setOf(a, b)).size == 2
}

