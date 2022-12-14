package se.johannesdahlgren.aoc22

import se.johannesdahlgren.aoc22.util.*

class Day14(private val fileName: String) {
    private val sandSourcePos = Pos(500, 0)

    fun calcUnitOfSandAtRest(): Int {
        val paths = readPathsFromInput()
        val restingSand = mutableListOf<Pos>()

        val maxY = paths.maxOf { it.getMaxY() }
        var sand = Pos(sandSourcePos.x, sandSourcePos.y)
        while (sand.y < maxY) {
            sand = Pos(sandSourcePos.x, sandSourcePos.y)
            var prevSandPos = sand.copy()

            while (prevSandPos != sand || sand.y < maxY) {

                if (!isBlocked(sand, Direction.UP, paths, restingSand)) {
                    sand.move(Direction.UP)
                }
                if (hasNotMoved(prevSandPos, sand) && !isBlocked(sand, Direction.UP_LEFT, paths, restingSand)) {
                    sand.move(Direction.UP_LEFT)
                }
                if (hasNotMoved(prevSandPos, sand) && !isBlocked(sand, Direction.UP_RIGHT, paths, restingSand)) {
                    sand.move(Direction.UP_RIGHT)
                }

                if (hasNotMoved(prevSandPos, sand)) {
                    restingSand.add(sand)
                    break
                }
                prevSandPos = sand.copy()
            }

//            printCave(paths, sands)
        }
        return restingSand.size
    }

    private fun hasNotMoved(prevSandPos: Pos, sand: Pos) = prevSandPos == sand

    private fun isBlocked(
        currentSand: Pos,
        nextDirection: Direction,
        paths: List<Path>,
        restingSand: List<Pos>
    ): Boolean {
        val nextPos = currentSand.getNextPos(nextDirection)
        return paths.any { nextPos.isOnPath(it) } || restingSand.any { it == nextPos }
    }

    private fun readPathsFromInput() = FileReader.readFileLinesAsString(fileName)
        .map { inputLine ->
            inputLine.split(" -> ").map { inputCoordinate ->
                val split = inputCoordinate.split(",")
                Pos(split.first().toInt(), split.last().toInt())
            }
        }
        .map { coordinates ->
            coordinates.zipWithNext { a, b -> if (a.x < b.x || a.y < b.y) Line(a, b) else Line(b, a) }
        }
        .map { Path(it) }

    private fun printCave(paths: List<Path>, restingSand: List<Pos>) {
        val maxX = paths.maxOf { it.getMaxX() }
        val maxY = paths.maxOf { it.getMaxY() }
        val minX = paths.minOf { it.getMinX() }
        val minY = paths.minOf { it.getMinY() }

        for (y in minY - 1..maxY + 1) {
            for (x in minX - 1..maxX + 1) {
                val pos = Pos(x, y)
                if (paths.any { pos.isOnPath(it) }) {
                    print("#")
                } else if (restingSand.contains(pos)) {
                    print("o")
                } else {
                    print(".")
                }
            }
            println()
        }
    }
}