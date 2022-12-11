package se.johannesdahlgren.aoc22

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.junit.jupiter.api.Test

class Day11Test {
    @Test
    fun part1Example() {
        val day11 = Day11("day11Example")
        assertThat(day11.monkeyBusiness(), Is.`is`(10605L))
    }

    @Test
    fun part1() {
        val day11 = Day11("day11")
        assertThat(day11.monkeyBusiness(), Is.`is`(118674L))
    }
}