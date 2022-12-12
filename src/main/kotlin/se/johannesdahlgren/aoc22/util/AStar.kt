package se.johannesdahlgren.aoc22.util

import java.util.*
import kotlin.math.abs
import kotlin.math.sign

data class Node(val x: Int, val y: Int) {
    var parent: Node? = null
}

data class NodeWithValue(val node: Node, val value: Char)


class AStar(
    private val map: List<List<NodeWithValue>>,
    private val diagonally: Boolean,
    private val start: Char = 'S',
    private val goal: Char = 'G',
    private val lowest: Char = '1',
    private val highest: Char = '9',
    private val blockedCharFun: (Char) -> Boolean = { _ -> false },
    private val blockedCharCharFun: (Char, Char) -> Boolean = { _, _ -> false }
) {

    private val gScore: MutableMap<Node, Int>
    private val fScore: MutableMap<Node, Int>

    init {
        val init = mutableMapOf<Node, Int>()
        for (x in map[0].indices) {
            for (y in map.indices) {
                init[Node(x, y)] = Int.MAX_VALUE
            }
        }
        gScore = init.toMutableMap()
        fScore = init.toMutableMap()
    }

    private fun reconstructPath(current: Node): List<Node> {
        val totalPath = mutableListOf<Node>()
        var next: Node? = current
        while (next != null) {
            totalPath.add(next)
            next = next.parent
        }
        return totalPath
    }

    private fun manhattan(current: Node, neighbor: Node): Int {
        return (abs(current.x - neighbor.x) + abs(current.y - neighbor.y))
    }

    private fun inBounds(neighbor: Node): Boolean {
        return neighbor.x >= 0 && neighbor.y >= 0 && neighbor.x < map[0].size && neighbor.y < map.size
    }

    private fun isBlocked(node: Node): Boolean {
        val valueOfNode = map.firstNotNullOf { row -> row.firstOrNull { it.node == node }?.value }
        return blockedCharFun(valueOfNode)
    }

    private fun cantEnter(current: Node, neighbor: Node): Boolean {
        var valueOfCurrent = map.firstNotNullOf { row -> row.firstOrNull { it.node == current }?.value }
        var valueOfNeighbor = map.firstNotNullOf { row -> row.firstOrNull { it.node == neighbor }?.value }

        if (valueOfCurrent == start) valueOfCurrent = lowest
        if (valueOfNeighbor == goal) valueOfNeighbor = highest

        return blockedCharCharFun(valueOfCurrent, valueOfNeighbor)
    }

    fun search(start: Node, goal: Node): List<Node> {
        val openSet = PriorityQueue<Node> { o1, o2 -> sign(fScore[o1]!!.toDouble() - fScore[o2]!!.toDouble()).toInt() }
        openSet.add(start)
        val visited = mutableSetOf<Node>()

        gScore[start] = 0
        fScore[start] = manhattan(start, goal)

        while (openSet.isNotEmpty()) {
            val current = openSet.poll() ?: break
            visited.add(current)
            if (current == goal) {
                return reconstructPath(current)
            }

            for (y in -1..1) {
                for (x in -1..1) {
                    if (x == 0 && y == 0) continue
                    if (!diagonally && abs(x) + abs(y) == 2) continue

                    val neighbor = Node(current.x + x, current.y + y).apply { parent = current }
                    if (visited.contains(neighbor)
                        || !inBounds(neighbor)
                        || isBlocked(neighbor)
                        || cantEnter(current, neighbor)
                    ) continue

                    val tentativeGScore = gScore[current]!! + manhattan(current, neighbor)
                    if (tentativeGScore < gScore[neighbor]!!) {
                        gScore[neighbor] = tentativeGScore
                        fScore[neighbor] = tentativeGScore + manhattan(neighbor, goal)
                        if (!openSet.contains(neighbor)) {
                            openSet.add(neighbor)
                        }
                    }
                }
            }

        }
        return listOf()
    }

    fun getNode(c: Char): Node {
        return map.firstNotNullOf { row -> row.firstOrNull { it.value == c }?.node }
    }
}