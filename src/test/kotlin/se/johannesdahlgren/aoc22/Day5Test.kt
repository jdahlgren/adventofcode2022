package se.johannesdahlgren.aoc22

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day5Test {
    @Test
    fun part1Example() {
        val day5 = Day5("day5Example")
        assertThat(day5.getCratesOnTop(), `is`("CMZ"))
    }

    @Test
    fun part1() {
        val day5 = Day5("day5")
        assertThat(day5.getCratesOnTop(), `is`("FZCMJCRHZ"))
    }

}