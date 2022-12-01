package se.johannesdahlgren

import se.johannesdahlgren.util.FileReader

class Day1(private val fileName: String) {

    data class Elf(var calories: Int)

    fun getCarriedCaloriesByElfCarryingMost(): Int {
        val input = FileReader.readFileAsStringList(fileName)
        var elf = Elf(0)
        val elves = mutableListOf<Elf>()
        for (calories in input) {
            if (calories == "") {
                elves.add(elf)
                elf = Elf(0)
            } else {
                elf.calories = elf.calories.plus(calories.toInt())
            }
        }

        return elves.maxOf { it.calories }
    }
}