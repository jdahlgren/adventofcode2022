package se.johannesdahlgren.aoc22

import se.johannesdahlgren.aoc22.util.Direction
import se.johannesdahlgren.aoc22.util.FileReader
import se.johannesdahlgren.aoc22.util.Pos

class Day9(private val fileName: String) {
    data class Instruction(val direction: Direction, val steps: Int)


    private var tailLocations = mutableSetOf<Pos>()

    fun calcTailUniqueVisits(numberOfKnots: Int): Int {
        val knots = (0 until numberOfKnots)
            .map { Pos(0, 0) }
            .toList()

        FileReader.readFileLinesAsString(fileName)
            .map {
                val split = it.split(" ")
                Instruction(Direction.fromString(split.first()), split.last().toInt())
            }
            .forEach { move(it, knots) }
        return tailLocations.size
    }

    private fun move(instruction: Instruction, knots: List<Pos>) {
        for (i in 0 until instruction.steps) {
            tailLocations.add(knots.last().copy())
            knots.first().move(instruction.direction)
            knots.windowed(2)
                .forEach { it.last().follow(it.first()) }
            tailLocations.add(knots.last().copy())
        }
    }
}
