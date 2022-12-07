package se.johannesdahlgren.aoc22

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.junit.jupiter.api.Test

class Day7Test {
    @Test
    fun part1Example() {
        val day7 = Day7("day7Example")
        assertThat(day7.calcTotalSize(100000), Is.`is`(95437))
    }

    @Test
    fun part1() {
        val day7 = Day7("day7")
        assertThat(day7.calcTotalSize(100000), Is.`is`(1206825))
    }

    @Test
    fun part2Example() {
        val day7 = Day7("day7Example")
        assertThat(day7.findSmallestFolder(30000000), Is.`is`(24933642))
    }

    @Test
    fun part2() {
        val day7 = Day7("day7")
        assertThat(day7.findSmallestFolder(30000000), Is.`is`(9608311))
    }
}