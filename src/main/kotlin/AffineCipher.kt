object AffineCipher {

    private const val M = 26
    private const val SIZE = 5

    fun encode(input: String, a: Int, b: Int): String {

        require(isCoprime(a, M)) { "Error: a and m must be coprime." }

        return input.filter { it.isLetterOrDigit() }
                .map { it.toLowerCase() }
                .map { if (it.isLetter()) ('a' + ((a * (it - 'a') + b).floorMod(M))) else it }
                .joinToString("")
                .chunked(SIZE)
                .joinToString(" ")
    }

    fun decode(input: String, a: Int, b: Int): String {

        require(isCoprime(a, M)) { "Error: a and m must be coprime." }

        return input.filter { it.isLetterOrDigit() }
                .map { it.toLowerCase() }
                .map { if (it.isLetter()) ('a' + ((getMMI(a) * (it - 'a' - b)).floorMod(M))) else it }
                .joinToString("")
    }

    private fun isCoprime(a: Int, b: Int): Boolean =
            when {
                (a == 1 || b == 1) -> true
                (a == 0 || b == 0) -> false
                (a > b) -> isCoprime(a % b, b)
                else -> isCoprime(a, b % a)
            }

    private fun getMMI(n: Int): Int =
            (1 until M).first { n * it % M == 1 }

    private fun Int.floorMod(y: Int) =
            Math.floorMod(this, y)

}
