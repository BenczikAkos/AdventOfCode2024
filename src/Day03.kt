fun main() {
    fun part1(input: String): Int {
        val regex = Regex("""mul\(([0-9]{1,3}),([0-9]{1,3})\)""")
        val matches = regex.findAll(input)
        return matches.sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
    }

    fun part2(input: String): Int {
        val mulRegex = """mul\(([0-9]{1,3}),([0-9]{1,3})\)""".toRegex()
        val doRegex = """do\(\)""".toRegex()
        val dontRegex = """don't\(\)""".toRegex()
        val doEndedInput = input + "do()"
        val doBlocks = doRegex.split(doEndedInput)
        val goodBlocks = doBlocks.map { dontRegex.split(it).first() }
        return goodBlocks.sumOf {enabledBlock -> mulRegex.findAll(enabledBlock).sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() } }
    }

    val input = readInput("Day03")
    input.sumOf { part1(it) }.println()
    part2(input.joinToString()).println()
}
