import java.math.BigInteger
import kotlin.system.exitProcess

const val DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"

fun toDecimal(number: String, multiplier: BigInteger, base: BigInteger): BigInteger {
    return when {
        (number.isEmpty()) -> BigInteger.ZERO
        number.last() !in DIGITS -> {
            println("Illegal number:\n${number.last()} is not a legal digit.")
            exitProcess(11)
        }
        DIGITS.indexOf(number.last()).toBigInteger() >= base -> {
            println("Illegal number\nDigit ${number.last()} is too big for the base $base")
            exitProcess(12)
        }
        else -> DIGITS.indexOf(number.last()).toBigInteger() * multiplier + toDecimal(number.dropLast(1), multiplier * base, base)
    }
}

fun decimalToBase(number: BigInteger, base: BigInteger): String {
    if (number == BigInteger.ZERO) return ""
    if (base > DIGITS.length.toBigInteger()) {
        println("Base $base is too big. We only have at most ${DIGITS.length} different digits.")
        println("Please provide a base that is at most ${DIGITS.length}")
        exitProcess(13)
    }
    return decimalToBase(number / base, base) + DIGITS[(number % base).toInt()]
}

fun fromBaseToBase(number: String, fromBase: BigInteger, toBase: BigInteger): String {
    val result = if (number.first() == '-') "-" + decimalToBase(toDecimal(number.drop(1), BigInteger.ONE, fromBase), toBase)
                 else decimalToBase(toDecimal(number, BigInteger.ONE, fromBase), toBase)
    return if (result in listOf("", "-")) "0" else result
}

fun main() {
//    val num1 = 110
//    println(num1.toString(16).uppercase())
//    println(num1.toString(8))
//    println(num1.toString(2))
//    println()

//    print("Enter number in decimal system: ")
//    val num = readln().toInt()
//    print("Enter target base: ")
//    val base = readln().toInt()
//    println("Conversion result: ${num.toString(base).uppercase()}")

//    println(DIGITS.length)


//    while (true) {
//        println("Converting a number from a given base to decimal")
//        print("What is the number: ")
//        val number = readln().uppercase()
//        print("What is the base: ")
//        val base = readln().toBigInteger()
//        println("$number in base $base = ${toDecimal(number, BigInteger.ONE, base)} in decimal")
//        println("Original number: ${decimalToBase(toDecimal(number, BigInteger.ONE, base), base)}\n")
//    }

    while (true) {
        println("Convert a number from one base to another")
        print("Number: ")
        val number = readln().uppercase()
        print("From base: ")
        val fromBase = readln().toBigInteger()
        print("To base: ")
        val toBase = readln().toBigInteger()
        println("The number $number in base $fromBase = ${fromBaseToBase(number, fromBase, toBase)} in base $toBase\n")
    }
}