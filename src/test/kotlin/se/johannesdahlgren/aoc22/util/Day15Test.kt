package se.johannesdahlgren.aoc22.util

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.junit.jupiter.api.Test

class Day15Test {
    @Test
    fun part1Example() {
        val day15 = Day15("day15Example")
        assertThat(day15.numberOfPosCantContainBeacon(10), Is.`is`(26))
    }

    @Test
    fun part1() {
        val day15 = Day15("day15")
        assertThat(day15.numberOfPosCantContainBeacon(2000000), Is.`is`(4879972))
    }
}