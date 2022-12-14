package se.johannesdahlgren.aoc22.util

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
            Direction.UP_LEFT -> {
                x--
                y++
            }

            Direction.UP_RIGHT -> {
                x++
                y++
            }
        }
    }

    fun getNextPos(direction: Direction): Pos {
        return when (direction) {
            Direction.UP -> Pos(x, y + 1)
            Direction.DOWN -> Pos(x, y - 1)
            Direction.RIGHT -> Pos(x + 1, y)
            Direction.LEFT -> Pos(x - 1, y)
            Direction.UP_LEFT -> Pos(x - 1, y + 1)
            Direction.UP_RIGHT -> Pos(x + 1, y + 1)
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

    fun isOnLine(line: Line): Boolean {
        val onX = line.startPos.x <= x && x <= line.endPos.x
        val onY = line.startPos.y <= y && y <= line.endPos.y
        return onX && onY
    }

    fun isOnPath(path: Path): Boolean {
        return path.lines.any { isOnLine(it) }
    }
}