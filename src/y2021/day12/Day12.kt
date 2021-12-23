package y2021.day12

import y2021.readInput

const val day = "12"
fun main() {

    fun mapNodes(input: List<String>): MutableMap<String, Node> {
        val nodes = mutableMapOf<String, Node>()
        input.forEach { s -> s.split("-").let { val (a, b) = it.map { x -> nodes.getOrPut(x) { Node(x) } }; a.link(b); b.link(a) } }
        return nodes
    }

    fun part1(input: List<String>): Int {
        val nodes = mapNodes(input)
        fun visit(past: List<String>, node: Node): List<List<String>> {
            return node.linked.mapNotNull {
                when {
                    it.name == "start" || (it.name[0].isLowerCase() && it.name in past) -> null
                    it.name == "end" -> listOf(past)
                    else -> visit(past + it.name, it)
                }
            }.flatten()
        }
        return visit(emptyList(), nodes["start"]!!).size
    }

    fun part2(input: List<String>): Int {
        val nodes = mapNodes(input)

        fun visit(past: List<String>, node: Node, flag: Boolean = false): List<List<String>> {
            return node.linked.mapNotNull {
                when {
                    it.name == "start" -> null
                    (it.name[0].isLowerCase() && it.name in past) -> if (flag) null else visit(past + it.name, it, true)
                    it.name == "end" -> listOf(past)
                    else -> visit(past + it.name, it, flag)
                }
            }.flatten()
        }

        return visit(emptyList(), nodes["start"]!!).size
    }

    fun printIfWrong(expected: Int, actual: Int) = (if (expected != actual) println("expected $expected, got $actual") else Unit).also { check(expected == actual) }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$day\\Day${day}_test")
    val testInput2 = readInput("day$day\\Day${day}_test2")
    val testInput3 = readInput("day$day\\Day${day}_test3")
    printIfWrong(10, part1(testInput))
    printIfWrong(19, part1(testInput2))
    printIfWrong(226, part1(testInput3))
    printIfWrong(36, part2(testInput))
    printIfWrong(103, part2(testInput2))
    printIfWrong(3509, part2(testInput3))

    val input = readInput("day$day\\Day$day")
    println(part1(input))
    println(part2(input))
}

class Node(val name: String) {
    val linked = mutableListOf<Node>()
    fun link(other: Node) = linked.add(other)
}
