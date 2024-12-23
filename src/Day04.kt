fun List<List<Char>>.checkXMAS(horizontalDirection: Int, verticalDirection: Int): Int {
    val solution = listOf('X', 'M', 'A', 'S')
    var solutionCount = 0
    for(rowNo in this.indices) {
        for (colNo in this.indices) {
            if (   rowNo + solution.indices.last*verticalDirection >= 0
                && rowNo + solution.indices.last*verticalDirection < this.size
                && colNo + solution.indices.last*horizontalDirection >= 0
                && colNo + solution.indices.last*horizontalDirection < this[0].size
                ) {
                var isSolution = true
                for (i in solution.indices) {
                    isSolution = isSolution && this[rowNo + i * verticalDirection][colNo + i * horizontalDirection] == solution[i]
                }
                if (isSolution) {
                    solutionCount++
                }
            }
        }
    }
    return solutionCount
}

fun <T> permutationsOfTwo(elements: List<T>): Sequence<Pair<T, T>> = sequence {
    for (i in elements.indices) {
        for (j in elements.indices) {
            yield(elements[i] to elements[j]) // Yield each pair
        }
    }
}

fun List<List<Char>>.checkPart2XMAS() : Int {
    var solutionCount = 0
    for (rowNo in this.indices) {
        for (colNo in this.indices) {
            if (this[rowNo][colNo] == 'A') {
                val NE = this.getOrNull(rowNo-1)?.getOrNull(colNo-1)
                val NW = this.getOrNull(rowNo-1)?.getOrNull(colNo+1)
                val SE = this.getOrNull(rowNo+1)?.getOrNull(colNo-1)
                val SW = this.getOrNull(rowNo+1)?.getOrNull(colNo+1)
                val corners = listOf(NE, NW, SE, SW)
                if (!corners.any { it == null} && !(NE?.equals(SW))!! && !(SE?.equals(NW))!!) {
                    if (corners.count{ it == 'M' }== 2 && corners.count { it == 'S' } == 2) {
                        solutionCount++
                    }
                }
            }
        }
    }
    return solutionCount
}

fun main() {
    fun part1(input : List<List<Char>>) : Int {
        return permutationsOfTwo(listOf(-1, 0, 1)).sumOf { (h, v) ->
            input.checkXMAS(h, v)
        }
    }

    val input = readInput("Day04")
    val grid = input.map{row -> row.map{it}}

    part1(grid).println()
    grid.checkPart2XMAS().println()
}
