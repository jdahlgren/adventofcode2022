package se.johannesdahlgren.aoc22

import se.johannesdahlgren.aoc22.util.*

class Day14(private val fileName: String) {
    private val sandSourcePos = Pos(500, 0)

    fun calcUnitOfSandAtRest(floorDistance: Int): Int {
        val paths = readPathsFromInput()
        val restingSand = mutableListOf<Pos>()

        val maxY = paths.maxOf { it.getMaxY() }
        val floorLevel = maxY + floorDistance
        var sand = Pos(sandSourcePos.x, sandSourcePos.y)

        //Hack! Part 1 does not work with floor?
        val floor = if (floorLevel != maxY) {
            Path(listOf(Line(Pos(-Int.MAX_VALUE, floorLevel), Pos(Int.MAX_VALUE, floorLevel))))
        } else {
            Path(listOf())
        }

        while (sand.y < floorLevel) {
            sand = Pos(sandSourcePos.x, sandSourcePos.y)
            var prevSandPos = sand.copy()

            while (prevSandPos != sand || sand.y < floorLevel) {

                if (!isBlocked(sand, Direction.UP, paths, restingSand, floor)) {
                    sand.move(Direction.UP)
                }
                if (hasNotMoved(prevSandPos, sand) && !isBlocked(sand, Direction.UP_LEFT, paths, restingSand, floor)) {
                    sand.move(Direction.UP_LEFT)
                }
                if (hasNotMoved(prevSandPos, sand) && !isBlocked(sand, Direction.UP_RIGHT, paths, restingSand, floor)) {
                    sand.move(Direction.UP_RIGHT)
                }

                if (hasNotMoved(prevSandPos, sand)) {
                    restingSand.add(sand)
                    if (sand == sandSourcePos) {
                        return restingSand.size
                    } else {
                        break
                    }
                }

                if (sand == sandSourcePos) {
                    return restingSand.size
                }
                prevSandPos = sand.copy()
            }

//            printCave(paths, restingSand, floor)
        }
        return restingSand.size
    }

    private fun hasNotMoved(prevSandPos: Pos, sand: Pos) = prevSandPos == sand

    private fun isBlocked(
        currentSand: Pos,
        nextDirection: Direction,
        paths: List<Path>,
        restingSand: List<Pos>,
        floor: Path
    ): Boolean {
        val nextPos = currentSand.getNextPos(nextDirection)
        return paths.any { nextPos.isOnPath(it) } || restingSand.any { it == nextPos }
                || nextPos.isOnPath(floor)
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

    private fun printCave(paths: List<Path>, restingSand: List<Pos>, floor: Path) {
        val maxX = paths.maxOf { it.getMaxX() }
        val maxY = paths.maxOf { it.getMaxY() }
        val minX = paths.minOf { it.getMinX() }

        for (y in 0..maxY + 3) {
            for (x in minX - 15..maxX + 15) {
                val pos = Pos(x, y)
                if (paths.any { pos.isOnPath(it) } || pos.isOnPath(floor)) {
                    print("#")
                } else if (restingSand.contains(pos)) {
                    print("o")
                } else if (pos == sandSourcePos) {
                    print("+")
                } else {
                    print(".")
                }
            }
            println()
        }
        println()
    }
}