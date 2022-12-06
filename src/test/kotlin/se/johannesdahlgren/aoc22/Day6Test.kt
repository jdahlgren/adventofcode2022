package se.johannesdahlgren.aoc22

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day6Test {
    @Test
    fun part1Example1() {
        val day6 = Day6("day6Example1")
        assertThat(day6.getStartOfPacketMarker(), `is`(7))
    }

    @Test
    fun part1Example2() {
        val day6 = Day6("day6Example2")
        assertThat(day6.getStartOfPacketMarker(), `is`(5))
    }

    @Test
    fun part1Example3() {
        val day6 = Day6("day6Example3")
        assertThat(day6.getStartOfPacketMarker(), `is`(6))
    }

    @Test
    fun part1Example4() {
        val day6 = Day6("day6Example4")
        assertThat(day6.getStartOfPacketMarker(), `is`(10))
    }

    @Test
    fun part1Example5() {
        val day6 = Day6("day6Example5")
        assertThat(day6.getStartOfPacketMarker(), `is`(11))
    }

    @Test
    fun part1() {
        val day6 = Day6("day6")
        assertThat(day6.getStartOfPacketMarker(), `is`(1109))
    }

    @Test
    fun part2Example1() {
        val day6 = Day6("day6Example1")
        assertThat(day6.getStartOfMessageMarker(), `is`(19))
    }

    @Test
    fun part2Example2() {
        val day6 = Day6("day6Example2")
        assertThat(day6.getStartOfMessageMarker(), `is`(23))
    }

    @Test
    fun part2Example3() {
        val day6 = Day6("day6Example3")
        assertThat(day6.getStartOfMessageMarker(), `is`(23))
    }

    @Test
    fun part2Example4() {
        val day6 = Day6("day6Example4")
        assertThat(day6.getStartOfMessageMarker(), `is`(29))
    }

    @Test
    fun part2Example5() {
        val day6 = Day6("day6Example5")
        assertThat(day6.getStartOfMessageMarker(), `is`(26))
    }

    @Test
    fun part2() {
        val day6 = Day6("day6")
        assertThat(day6.getStartOfMessageMarker(), `is`(3965))
    }
}