package se.johannesdahlgren.aoc22

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.junit.jupiter.api.Test

class Day12Test {
    @Test
    fun part1Example() {
        val day12 = Day12("day12Example")
        assertThat(day12.fewestStepToBestSignal(), Is.`is`(31))
    }

    @Test
    fun part1() {
        val day12 = Day12("day12")
        assertThat(day12.fewestStepToBestSignal(), Is.`is`(408))
    }
}