package se.johannesdahlgren.aoc22

import se.johannesdahlgren.aoc22.util.FileReader
import se.johannesdahlgren.aoc22.util.Pos3D

class Day18(private val fileName: String) {
    fun getSurfaceArea(): Int {
        val lavaCubes = FileReader.readFileLinesAsString(fileName)
            .map { it.split(",") }
            .map { Pos3D(it[0].toInt(), it[1].toInt(), it[2].toInt()) }

        return lavaCubes
            .sumOf { getSurfaceArea(it, lavaCubes) }
    }

    private fun getSurfaceArea(lavaCube: Pos3D, lavaCubes: List<Pos3D>): Int {
        return 6 - lavaCubes
            .filter { lavaCube != it }
            .count { lavaCube.manhattanDistance(it) == 1 }

    }
}