package se.johannesdahlgren.aoc22

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day4Test {

    @Test
    fun part1Example() {
        val day4 = Day4("day4Example")
        assertThat(day4.numberOfAssignmentPairsFullyContained(), `is`(2))
    }

    @Test
    fun part1() {
        val day4 = Day4("day4")
        assertThat(day4.numberOfAssignmentPairsFullyContained(), `is`(515))
    }
}