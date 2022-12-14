package se.johannesdahlgren.aoc22.util

data class Line(val startPos: Pos, val endPos: Pos) {
    fun getMaxX(): Int {
        return listOf(startPos, endPos).maxOf { it.x }
    }

    fun getMaxY(): Int {
        return listOf(startPos, endPos).maxOf { it.y }
    }

    fun getMinX(): Int {
        return listOf(startPos, endPos).minOf { it.x }
    }

    fun getMinY(): Int {
        return listOf(startPos, endPos).minOf { it.y }
    }
}
