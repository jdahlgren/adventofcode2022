package se.johannesdahlgren.aoc22.util

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.junit.jupiter.api.Test

class AStarTest {

    private val start = 'S'
    private val goal = 'G'

    private val charMap: List<List<NodeWithValue>>
        get() {
            val map = """
            S......
            .######
            .......
            ######.
            .......
            .######
            ......G
        """.trimIndent()
                .lines()
                .mapIndexed { y, line ->
                    line.toCharArray().mapIndexed { x, c -> NodeWithValue(Node(x, y), c) }
                }
            return map
        }

    private val nonSquareCharMap: List<List<NodeWithValue>>
        get() {
            val map = """
            S......
            .######
            .......
            ######.
            .......
            .......
            .......
            .######
            ......G
        """.trimIndent()
                .lines()
                .mapIndexed { y, line ->
                    line.toCharArray().mapIndexed { x, c -> NodeWithValue(Node(x, y), c) }
                }
            return map
        }

    private val intMap: List<List<NodeWithValue>>
        get() {
            val map = """
            S111111
            1999999
            1111111
            9999991
            4432111
            5999999
            556789G
        """.trimIndent()
                .lines()
                .mapIndexed { y, line ->
                    line.toCharArray().mapIndexed { x, c -> NodeWithValue(Node(x, y), c) }
                }
            return map
        }

    @Test
    fun searchDiagonal() {
        val aStar = AStar(charMap, true, blockedCharFun = 0.toChar()::equals)
        val start = aStar.getNode(start)
        val goal = aStar.getNode(goal)
        val distance = aStar.search(start, goal)
        assertThat(distance.size, Is.`is`(7))
    }

    @Test
    fun searchNonDiagonal() {
        val aStar = AStar(charMap, false, blockedCharFun = 0.toChar()::equals)
        val start = aStar.getNode(start)
        val goal = aStar.getNode(goal)
        val distance = aStar.search(start, goal)
        assertThat(distance.size, Is.`is`(13))
    }

    @Test
    fun searchDiagonalNonSquare() {
        val aStar = AStar(nonSquareCharMap, true, blockedCharFun = 0.toChar()::equals)
        val start = aStar.getNode(start)
        val goal = aStar.getNode(goal)
        val distance = aStar.search(start, goal)
        assertThat(distance.size, Is.`is`(9))
    }

    @Test
    fun searchNonDiagonalNonSquare() {
        val aStar = AStar(nonSquareCharMap, false, blockedCharFun = 0.toChar()::equals)
        val start = aStar.getNode(start)
        val goal = aStar.getNode(goal)
        val distance = aStar.search(start, goal)
        assertThat(distance.size, Is.`is`(15))
    }

    @Test
    fun searchDiagonalBlock() {
        val aStar = AStar(charMap, false, blockedCharFun = '#'::equals)
        val start = aStar.getNode(start)
        val goal = aStar.getNode(goal)
        val distance = aStar.search(start, goal)
        assertThat(distance.size, Is.`is`(25))
    }

    @Test
    fun searchNonDiagonalBlock() {
        val aStar = AStar(charMap, true, blockedCharFun = '#'::equals)
        val start = aStar.getNode(start)
        val goal = aStar.getNode(goal)
        val distance = aStar.search(start, goal)
        println(distance)
        assertThat(distance.size, Is.`is`(20))
    }

    @Test
    fun searchDiagonalInt() {
        val aStar = AStar(intMap, true) { a, b -> a + 1 < b }
        val start = aStar.getNode(start)
        val goal = aStar.getNode(goal)
        val distance = aStar.search(start, goal)
        println(distance)
        assertThat(distance.size, Is.`is`(20))
    }
}