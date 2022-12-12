package se.johannesdahlgren.aoc22

import se.johannesdahlgren.aoc22.util.AStar
import se.johannesdahlgren.aoc22.util.FileReader
import se.johannesdahlgren.aoc22.util.Node
import se.johannesdahlgren.aoc22.util.NodeWithValue

class Day12(private val fileName: String) {
    private val startChar = 'S'
    private val goalChar = 'E'
    fun fewestStepToBestSignal(): Int {
        val map = FileReader.readFileLinesAsString(fileName)
            .mapIndexed { y, line ->
                line.toCharArray().mapIndexed { x, c -> NodeWithValue(Node(x, y), c) }
            }

        val aStar = AStar(map, false, startChar, goalChar, 'a', 'z', blockedCharCharFun = { a, b -> a + 1 < b })
        val start = aStar.getNode(startChar)
        val goal = aStar.getNode(goalChar)
        val searchResult = aStar.search(start, goal)

        return searchResult.size - 1
    }
}