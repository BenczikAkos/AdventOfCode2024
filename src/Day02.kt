fun main() {
    fun List<Int>.isSafe() : Int {
        val diffs = this.zip(this.subList(1, this.size)) {a, b -> b-a}
        if  ( diffs.any { it == 0 } || diffs.any { kotlin.math.abs(it) > 3 } ||
            ( diffs.any { it < 0 } && diffs.any { it > 0 } ) )
            return 0
        return 1
    }

    fun List<Int>.isSafeWithoutOne() : Int {
        for (i in indices) {
            val copy = this.toMutableList()
            copy.removeAt(i)
            if (copy.isSafe() == 1) return 1
        }
        return 0
    }

    fun part1(input: List<String>): Int {
        return input.sumOf {report -> report.split(" ").map{ it.toInt() }.isSafe() }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf {report -> report.split(" ").map{ it.toInt() }.isSafeWithoutOne() }
    }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
