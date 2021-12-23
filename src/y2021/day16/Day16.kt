package y2021.day16

import y2021.readInputAsString
import java.math.BigInteger

const val day = "05"
fun main() {
    fun part1(input: String): Int = parseInput(input.toBinary()).first?.versionSum() ?: 0

//    fun part2(input: List<String>): Int {
//        return input.size
//    }

    // test if implementation meets criteria from the description, like:
    check(part1("EE00D40C823060").also(::println) == 14)
    check(part1("8A004A801A8002F478") == 18)
    check(part1("620080001611562C8802118E34") == 12)
    check(part1("C0015000016115A2E0802F182340") == 23)
    check(part1("A0016C880162017C3686B18A3D4780") == 31)
//    check(part2(testInput) == 1)

    val input = readInputAsString("day$day\\Day$day")
    println(part1(input))
//    println(part2(input))
}

fun String.toBinary(): String = toBigInteger(16).toString(2)
fun String.foo(i: Int): Pair<BigInteger, Int> = if (this.length > 4) (take(i).windowed(5, 5).joinToString("") { it.drop(1) }.toBigInteger(2) to i) else (BigInteger.ZERO to this.length)
fun String.parseValue(): Pair<BigInteger, Int> = foo((0..lastIndex step 5).first { this[it] == '0' } + 1)


class Node(input: String) {
    private val version: Int
    private val typeId: Int
    private val chars: Boolean
    private val length: Int
    private val value: BigInteger
    private val end: Int
    private val subNodes = mutableListOf<Node>()

    init {
        version = input.take(3).toInt(2)
        typeId = input.drop(3).take(3).toInt(2)
        chars = input[6] == '0'

        when (typeId) {
            4 -> {
                input.drop(6).parseValue().also { value = it.first;length = 0; end = 6 + it.second }
            }
            else -> {
                value = BigInteger.ZERO
                when {
                    chars -> {
                        length = input.drop(7).take(15).toInt(2)
                        end = 22 + length
                        val subInput = input.drop(22).take(length)
                        var i = 0
                        while (i in subInput.indices) {
                            parseInput(subInput, i).also { it.first?.let { it1 -> subNodes.add(it1) };i = it.second }
                        }
                    }
                    else -> {
                        length = input.drop(7).take(11).toInt(2)
                        var foo = 0
                        var i = 0
                        while (i++ < length) {
                            val (a, b) = parseInput(input.drop(22 + length + foo))
                            if (a != null) {
                                subNodes.add(a)
                            }
                            foo += b
                        }
                        end = 22 + length + foo
                    }
                }
            }
        }
    }

    fun getSize(): Int = end
    fun versionSum(): Int = version + subNodes.sumOf { it.version }
}

fun parseInput(input: String, index: Int = 0): Pair<Node?, Int> {
    if(index < input.length) {
        val root = Node(input.drop(index))
        return root to root.getSize() + index
    } else return null to input.lastIndex+1
}
