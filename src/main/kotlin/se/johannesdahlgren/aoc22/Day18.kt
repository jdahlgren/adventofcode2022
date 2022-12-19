package se.johannesdahlgren.aoc22

import se.johannesdahlgren.aoc22.util.FileReader
import se.johannesdahlgren.aoc22.util.Pos3D

class Day18(private val fileName: String) {
    private val sidesOfCube = 6

    private fun get3dPosInput() = FileReader.readFileLinesAsString(fileName)
        .map { it.split(",") }
        .map { Pos3D(it[0].toInt(), it[1].toInt(), it[2].toInt()) }

    fun getSurfaceArea(): Int {
        val lavaCubes = get3dPosInput()
        return lavaCubes
            .sumOf { getSurfaceArea(it, lavaCubes) }
    }

    private fun getSurfaceArea(lavaCube: Pos3D, lavaCubes: List<Pos3D>): Int {
        return sidesOfCube - lavaCubes
            .filter { lavaCube != it }
            .count { lavaCube.manhattanDistance(it) == 1 }

    }
}