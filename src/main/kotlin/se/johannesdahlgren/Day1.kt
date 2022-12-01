package se.johannesdahlgren

import se.johannesdahlgren.util.FileReader

class Day1(private val fileName: String) {

    data class Elf(var calories: Int)

    fun getCarriedCaloriesByElfCarryingMost(): Int {
        val elves = calculateCarriedCaloriesByElf()
        return elves.maxOf { it.calories }
    }

    fun getCarriedCaloriesByTop3ElvesCarryingMost(): Int {
        val elves = calculateCarriedCaloriesByElf()
        elves.sortByDescending { it.calories }
        return elves
                .take(3)
                .sumOf { it.calories }
    }

    private fun calculateCarriedCaloriesByElf(): MutableList<Elf> {
        return FileReader.readFileAsStringSplitEmptyLine(fileName)
                .map { food ->
                    food.lines()
                            .map { calories -> calories.toInt() }
                }
                .map { Elf(it.sum()) }
                .toMutableList()
    }
}