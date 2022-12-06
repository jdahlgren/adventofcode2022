package se.johannesdahlgren.aoc22

import se.johannesdahlgren.aoc22.util.FileReader

class Day6(private val fileName: String) {
    private val packetMarker = 4
    private val messageMarker = 14

    fun getStartOfPacketMarker(): Int {
        return getStartOf(packetMarker)
    }

    fun getStartOfMessageMarker(): Int {
        return getStartOf(messageMarker)
    }

    private fun getStartOf(marker: Int): Int {
        return FileReader.readFileAsString(fileName)
            .windowed(marker)
            .indexOfFirst { it.toSet().size == it.length }
            .plus(marker)
    }
}