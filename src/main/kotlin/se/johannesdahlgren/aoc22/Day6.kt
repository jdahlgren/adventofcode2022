package se.johannesdahlgren.aoc22

import se.johannesdahlgren.aoc22.util.FileReader

class Day6(private val fileName: String) {
    private val windowSize = 4

    fun getStartOfPacketMarker(): Int {
        return FileReader.readFileAsString(fileName)
            .windowed(windowSize)
            .indexOfFirst { it.toSet().size == it.length }
            .plus(windowSize)
    }
}