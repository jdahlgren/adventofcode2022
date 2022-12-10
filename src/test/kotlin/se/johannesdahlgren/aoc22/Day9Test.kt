package se.johannesdahlgren.aoc22

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.junit.jupiter.api.Test

class Day9Test {
    @Test
    fun part1Example() {
        val day9 = Day9("day9Example")
        assertThat(day9.calcTailUniqueVisits(2), Is.`is`(13))
    }

    @Test
    fun part1() {
        val day9 = Day9("day9")
        assertThat(day9.calcTailUniqueVisits(2), Is.`is`(6337))
    }

    @Test
    fun part2Example() {
        val day9 = Day9("day9Example")
        assertThat(day9.calcTailUniqueVisits(10), Is.`is`(1))
    }

    @Test
    fun part2Example2() {
        val day9 = Day9("day9Example2")
        assertThat(day9.calcTailUniqueVisits(10), Is.`is`(36))
    }

    @Test
    fun part2() {
        val day9 = Day9("day9")
        assertThat(day9.calcTailUniqueVisits(10), Is.`is`(2455))
    }
}