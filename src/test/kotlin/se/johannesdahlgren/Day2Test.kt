package se.johannesdahlgren

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun part1Example() {
        val day2 = Day2("day2Example")
        assertThat(day2.getTotalScore(), `is`(15))
    }

    @Test
    fun part1() {
        val day2 = Day2("day2")
        assertThat(day2.getTotalScore(), `is`(12855))
    }
}