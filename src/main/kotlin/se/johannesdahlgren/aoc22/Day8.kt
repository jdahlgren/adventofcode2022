package se.johannesdahlgren.aoc22

import se.johannesdahlgren.aoc22.util.Direction
import se.johannesdahlgren.aoc22.util.FileReader
import se.johannesdahlgren.aoc22.util.Pos

class Day8(val fileName: String) {

    data class GridElement(val pos: Pos, val value: Int, var visible: Int) {
        fun isVisible(): Boolean {
            return visible > 0
        }
    }

    data class Grid(private val data: List<List<GridElement>>) {
        fun getRows(): List<List<GridElement>> {
            return data
        }

        fun getCols(): List<List<GridElement>> {
            val temp = data[0].indices
                .map { mutableListOf<GridElement>() }
                .toList()
            data.forEach { rows ->
                rows.forEachIndexed { index, col ->
                    temp[index].add(col)
                }
            }
            return temp.map {
                it.toList()
            }
        }

        fun getNextElementIn(direction: Direction, currentElement: GridElement): GridElement? {
            val nextPos = currentElement.pos.getNextPos(direction)
            return data.map { row ->
                row.map { it }
            }
                .flatten()
                .firstOrNull { it.pos == nextPos }
        }
    }

    private fun visit(inner: List<List<GridElement>>) {
        return inner.drop(1).dropLast(1)
            .forEach { visitInner(it) }
    }

    private fun visitReversed(inner: List<List<GridElement>>) {
        return inner.drop(1).dropLast(1)
            .forEach { visitInner(it.reversed()) }
    }

    private fun visitInner(elements: List<GridElement>) {
        var prev = elements.first()
        elements.drop(1).dropLast(1)
            .forEach {
                if (it.value > prev.value) {
                    it.visible++
                    prev = it
                }
            }
    }

    fun visibleTrees(): Int {
        val heightMap = FileReader.readFileLinesAsString(fileName)
            .mapIndexed { i, line ->
                line.toCharArray().mapIndexed { j, col -> GridElement(Pos(i, j), col.digitToInt(), 0) }
            }

        val edges = heightMap.size * 2 - 2 + heightMap[0].size * 2 - 2

        val grid = Grid(heightMap)
        visit(grid.getRows())
        visitReversed(grid.getRows())
        visit(grid.getCols())
        visitReversed(grid.getCols())

        return edges + grid.getRows().map { row -> row.map { it } }.flatten().count { it.isVisible() }
    }

    fun scenicScore(): Int {
        val heightMap = FileReader.readFileLinesAsString(fileName)
            .mapIndexed { i, line ->
                line.toCharArray().mapIndexed { j, col -> GridElement(Pos(j, i), col.digitToInt(), 0) }
            }
        val grid = Grid(heightMap)
        return grid.getRows().map { row -> row.map { it } }
            .flatten()
            .map { getScenicScore(it, grid) }
            .max()
    }

    private fun getScenicScore(it: GridElement, grid: Grid): Int {
        return getSubScenicScore(Direction.UP, it, grid)
            .times(getSubScenicScore(Direction.DOWN, it, grid))
            .times(getSubScenicScore(Direction.RIGHT, it, grid))
            .times(getSubScenicScore(Direction.LEFT, it, grid))
    }

    private fun getSubScenicScore(direction: Direction, gridElement: GridElement, grid: Grid): Int {
        val height = gridElement.value
        var subScenicScore = 0
        var nextElement = grid.getNextElementIn(direction, gridElement)
        while (nextElement != null && nextElement.value < height) {
            subScenicScore++
            nextElement = grid.getNextElementIn(direction, nextElement)
        }
        if (nextElement != null) {
            subScenicScore++
        }
        return subScenicScore
    }

}