package se.johannesdahlgren.aoc22

import se.johannesdahlgren.aoc22.util.FileReader

const val LOWERCASE_A_ASCII_START = 96
const val UPPERCASE_A_ASCII_START = 64
const val UPPERCASE_AFTER_LOWER = 26

class Day3(private val fileName: String) {

    data class Rucksack(val firstCompartment: CharSequence, val secondCompartment: CharSequence)

    fun prioritiesSum(): Int {
        return FileReader.readFileLinesAsString(fileName)
            .map { Rucksack(it.subSequence(0, it.length / 2), it.subSequence(it.length / 2, it.length)) }
            .map { findCommonItems(it) }
            .sumOf { calcPriority(it) }
    }

    private fun calcPriority(itemType: Char): Int {
        return if (itemType.isLowerCase()) {
            itemType.code.minus(LOWERCASE_A_ASCII_START)
        } else {
            itemType.code.minus(UPPERCASE_A_ASCII_START - UPPERCASE_AFTER_LOWER)
        }
    }

    private fun findCommonItems(rucksack: Rucksack): Char {
        return rucksack.firstCompartment
            .first { rucksack.secondCompartment.contains(it) }
    }

}