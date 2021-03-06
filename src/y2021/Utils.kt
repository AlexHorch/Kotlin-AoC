package y2021

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInputAsString(name: String) = File("src//y2021", "$name.txt").readText().trim()
fun readInput(name: String) = File("src//y2021", "$name.txt").readLines()
fun readInputAsIntegers(name: String) = File("src//y2021", "$name.txt").readLines().first().split(",").map(String::toInt)
fun readInputAsDigitGrid(name: String) = File("src//y2021", "$name.txt").readLines().map { line -> line.map(Char::digitToInt) }

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
