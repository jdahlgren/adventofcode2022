package se.johannesdahlgren

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Day1Test {

    private lateinit var day1: Day1

    @BeforeEach
    fun setUp() {
        day1 = Day1()
    }

    @Test
    fun calc() {
        assertThat(day1.calc(), `is`("hej"))
    }
}