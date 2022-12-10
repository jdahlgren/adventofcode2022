package se.johannesdahlgren.aoc22

import se.johannesdahlgren.aoc22.util.FileReader

class Day9(private val fileName: String) {
    enum class Direction(val short: String) { UP("U"), DOWN("D"), RIGHT("R"), LEFT("L") }
    data class Instruction(val direction: Direction, val steps: Int)
    data class Pos(var x: Int, var y: Int) {
        private fun isAdjacent(otherPos: Pos): Boolean {
            return otherPos.x in this.x - 1..this.x + 1 && otherPos.y in this.y - 1..this.y + 1
        }

        private fun isOverlapping(otherPos: Pos): Boolean {
            return otherPos.x == this.x && otherPos.y == this.y
        }

        private fun isAdjacentOrOverlapping(otherPos: Pos): Boolean {
            return isAdjacent(otherPos) || isOverlapping(otherPos)
        }

        fun move(direction: Direction) {
            when (direction) {
                Direction.UP -> y++
                Direction.DOWN -> y--
                Direction.RIGHT -> x++
                Direction.LEFT -> x--
            }
        }

        fun follow(otherPos: Pos) {
            if (!isAdjacentOrOverlapping(otherPos)) {
                when {
                    this.x == otherPos.x && this.y > otherPos.y -> this.y--
                    this.x == otherPos.x && this.y < otherPos.y -> this.y++
                    this.y == otherPos.y && this.x > otherPos.x -> this.x--
                    this.y == otherPos.y && this.x < otherPos.x -> this.x++
                    this.x < otherPos.x && this.y < otherPos.y -> {
                        this.x++
                        this.y++
                    }

                    this.x < otherPos.x && this.y > otherPos.y -> {
                        this.x++
                        this.y--
                    }

                    this.x > otherPos.x && this.y < otherPos.y -> {
                        this.x--
                        this.y++
                    }

                    this.x > otherPos.x && this.y > otherPos.y -> {
                        this.x--
                        this.y--
                    }
                }
            }
        }
    }

    private var tailLocations = mutableSetOf<Pos>()

    fun calcTailUniqueVisits(numberOfKnots: Int): Int {
        val knots = (0 until numberOfKnots)
            .map { Pos(0, 0) }
            .toList()

        FileReader.readFileLinesAsString(fileName)
            .map {
                val split = it.split(" ")
                Instruction(split.first().doDirection(), split.last().toInt())
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

private fun String.doDirection(): Day9.Direction {
    return Day9.Direction.values().first { this == it.short }
}
