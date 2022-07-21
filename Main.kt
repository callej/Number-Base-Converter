package converter

import java.math.RoundingMode

const val DECIMALS = 5

fun fraction(frac: String, from: Int, to: Int): String {
    var value = 0.0.toBigDecimal()
    var divisor = from.toBigInteger()
    for (index in frac.indices) {
        value += frac[index].toString().toBigInteger(from).toBigDecimal().divide(divisor.toBigDecimal(), 256, RoundingMode.HALF_DOWN).stripTrailingZeros()
        divisor *= from.toBigInteger()
    }
    var decimals = ""
    repeat(DECIMALS) {
        value *= to.toBigDecimal()
        decimals += value.toInt().toString(to)
        value -= value.toInt().toBigDecimal()
    }
    return decimals
}

fun result(num: String, from: Int, to: Int): String {
    return if (num.contains('.')) {
        val (int, frac) = num.split('.')
        int.toBigInteger(from).toString(to).uppercase() + "." + fraction(frac, from, to).uppercase()
    }
    else {
        num.toBigInteger(from).toString(to).uppercase()
    }
}

fun main() {
    while (true) {
        print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ")
        when (val bases = readln(). split(" ")) {
            listOf("/exit") -> break
            else -> while (true) {
                print("Enter number in base ${bases.first()} to convert to base ${bases.last()} (To go back type /back) ")
                when (val num = readln()) {
                    "/back" -> break
                    else -> println("Conversion result: ${result(num, bases.first().toInt(), bases.last().toInt())}\n")
                }
            }
        }
    }
}