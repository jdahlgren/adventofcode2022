package se.johannesdahlgren.aoc22

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.junit.jupiter.api.Test

class Day14Test {

    @Test
    fun part1Example() {
        val day14 = Day14("day14Example")
        assertThat(day14.calcUnitOfSandAtRest(), Is.`is`(24))
    }

    @Test
    fun part1() {
        val day14 = Day14("day14")
        assertThat(day14.calcUnitOfSandAtRest(), Is.`is`(737))
    }
}