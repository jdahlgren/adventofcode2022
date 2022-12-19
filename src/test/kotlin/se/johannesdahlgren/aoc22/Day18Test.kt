package se.johannesdahlgren.aoc22

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.junit.jupiter.api.Test

class Day18Test {

    @Test
    fun part1Example() {
        val day18 = Day18("day18Example")
        assertThat(day18.getSurfaceArea(), Is.`is`(10))
    }

    @Test
    fun part1Example2() {
        val day18 = Day18("day18Example2")
        assertThat(day18.getSurfaceArea(), Is.`is`(64))
    }

    @Test
    fun part1() {
        val day18 = Day18("day18")
        assertThat(day18.getSurfaceArea(), Is.`is`(4364))
    }
}