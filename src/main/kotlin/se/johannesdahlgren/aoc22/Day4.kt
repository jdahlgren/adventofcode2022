package se.johannesdahlgren.aoc22

import se.johannesdahlgren.aoc22.util.FileReader
import java.util.stream.IntStream
import kotlin.streams.toList

class Day4(private val fileName: String) {
    private val regex = Regex("(\\d+)-(\\d+),(\\d+)-(\\d+)")

    data class Section(val first: List<Int>, val second: List<Int>)

    fun numberOfAssignmentPairsFullyContained(): Int {
        return createSections()
            .count {
                val intersection = it.first.intersect(it.second.toSet())
                intersection.containsAll(it.first) || intersection.containsAll(it.second)
            }
    }

    fun numberOfAssignmentPairsOverlap(): Int {
        return createSections()
            .count {
                it.first.intersect(it.second.toSet()).isNotEmpty()
            }
    }

    private fun createSections() = FileReader.readFileLinesAsString(fileName)
        .map { regex.find(it) }
        .map {
            Section(
                IntStream.range(it!!.groupValues[1].toInt(), it.groupValues[2].toInt() + 1).toList(),
                IntStream.range(it.groupValues[3].toInt(), it.groupValues[4].toInt() + 1).toList()
            )
        }
}