package se.johannesdahlgren.aoc22.util

enum class Direction(val short: String) {
    UP("U"), DOWN("D"), RIGHT("R"), LEFT("L");

    companion object {
        fun fromString(value: String): Direction {
            return Direction.values().first { value == it.short }
        }
    }
}