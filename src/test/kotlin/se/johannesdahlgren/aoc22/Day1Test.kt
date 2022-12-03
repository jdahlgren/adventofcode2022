package se.johannesdahlgren.aoc22

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day1Test {

    @Test
    fun part1Example() {
        val day1 = Day1("day1Example")
        assertThat(day1.getCarriedCaloriesByElfCarryingMost(), `is`(24000))
    }

    @Test
    fun part1() {
        val day1 = Day1("day1")
        assertThat(day1.getCarriedCaloriesByElfCarryingMost(), `is`(72511))
    }

    @Test
    fun part2Example() {
        val day1 = Day1("day1Example")
        assertThat(day1.getCarriedCaloriesByTop3ElvesCarryingMost(), `is`(45000))
    }

    @Test
    fun part2() {
        val day1 = Day1("day1")
        assertThat(day1.getCarriedCaloriesByTop3ElvesCarryingMost(), `is`(212117))
    }
}