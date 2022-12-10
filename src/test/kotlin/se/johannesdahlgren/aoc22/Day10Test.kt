package se.johannesdahlgren.aoc22

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.junit.jupiter.api.Test

class Day10Test {
    @Test
    fun part1Example() {
        val day10 = Day10("day10Example")
        assertThat(day10.getSignalStrength(), Is.`is`(13140))
    }

    @Test
    fun part1() {
        val day10 = Day10("day10")
        assertThat(day10.getSignalStrength(), Is.`is`(17380))
    }

    @Test
    fun part2Example() {
        val day10 = Day10("day10Example")
        val expectedResult = """
            ##..##..##..##..##..##..##..##..##..##..
            ###...###...###...###...###...###...###.
            ####....####....####....####....####....
            #####.....#####.....#####.....#####.....
            ######......######......######......####
            #######.......#######.......#######.....
        """.trimIndent()
        assertThat(day10.drawCRTScreen(), Is.`is`(expectedResult))
    }

    @Test
    fun part2() {
        val day10 = Day10("day10")
        val expectedResult = """
            ####..##...##..#..#.####.###..####..##..
            #....#..#.#..#.#..#....#.#..#.#....#..#.
            ###..#....#....#..#...#..#..#.###..#....
            #....#.##.#....#..#..#...###..#....#....
            #....#..#.#..#.#..#.#....#.#..#....#..#.
            #.....###..##...##..####.#..#.####..##..
        """.trimIndent()
        assertThat(day10.drawCRTScreen(), Is.`is`(expectedResult))
    }
}