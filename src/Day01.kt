import kotlin.math.abs

fun main() {

    fun List<String>.splitBySpace(): Pair<MutableList<Int>, MutableList<Int>> {
        val mList1 = mutableListOf<Int>()
        val mList2 = mutableListOf<Int>()
        this.forEach {
            mList1.add(it.split(" ").first().toInt())
            mList2.add(it.split(" ").last().toInt())
        }
        return mList1 to mList2
    }

    fun part1(input: List<String>): Int {
        val (list1, list2) = input.splitBySpace()
        return list1.sorted().zip(list2.sorted()) { a, b -> abs(a - b) }.sum()
    }

    fun part2(input: List<String>): Int {
        //sum(every unique number * nbOccurencesInLeft * nbOccurencesRight)
        val (list1, list2) = input.splitBySpace()
        val uniqueNumbers: Set<Int> = list1.toSet()
        return uniqueNumbers.sumOf { unique ->
            unique *
                    list1.map { if (it == unique) 1 else 0 }.sum() *
                    list2.map { if (it == unique) 1 else 0 }.sum()
        }
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
