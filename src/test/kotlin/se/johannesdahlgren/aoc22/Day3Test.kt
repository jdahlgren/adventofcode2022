package se.johannesdahlgren.aoc22

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day3Test {

    @Test
    fun part1Example() {
        val day3 = Day3("day3Example")
        assertThat(day3.prioritiesSum(), `is`(157))
    }

    @Test
    fun part1() {
        val day3 = Day3("day3")
        assertThat(day3.prioritiesSum(), `is`(8176))
    }

    @Test
    fun part2Example() {
        val day3 = Day3("day3Example")
        assertThat(day3.prioritiesSumElfGroup(), `is`(70))
    }

    @Test
    fun part2() {
        val day3 = Day3("day3")
        assertThat(day3.prioritiesSumElfGroup(), `is`(2689))
    }
}