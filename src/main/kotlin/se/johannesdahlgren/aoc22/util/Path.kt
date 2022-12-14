package se.johannesdahlgren.aoc22.util

data class Path(val lines: List<Line>) {
    fun getMaxX(): Int {
        return lines.maxOf { it.getMaxX() }
    }

    fun getMaxY(): Int {
        return lines.maxOf { it.getMaxY() }
    }

    fun getMinX(): Int {
        return lines.minOf { it.getMinX() }
    }

    fun getMinY(): Int {
        return lines.minOf { it.getMinY() }
    }
}
