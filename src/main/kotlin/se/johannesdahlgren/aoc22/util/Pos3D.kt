package se.johannesdahlgren.aoc22.util

import kotlin.math.abs

data class Pos3D(val x: Int, val y: Int, val z: Int) {

    fun manhattanDistance(other: Pos3D) = abs(x - other.x) + abs(y - other.y) + abs(z - other.z)
}