package se.johannesdahlgren.aoc22

import se.johannesdahlgren.aoc22.util.FileReader

class Day10(private val fileName: String) {
    data class Cycle(val index: Int, val registerValue: Int)

    fun getSignalStrength(): Int {
        var currentRegisterValue = 1
        var currentCycle = 1
        return FileReader.readFileLinesAsString(fileName)
            .asSequence()
            .map {
                if (it == "noop") {
                    listOf(Cycle(currentCycle++, currentRegisterValue))
                } else {
                    val mutableListOf = mutableListOf(Cycle(currentCycle++, currentRegisterValue))
                    val toAdd = it.split(" ").last().toInt()
                    currentRegisterValue += toAdd
                    mutableListOf.add(Cycle(currentCycle++, currentRegisterValue))
                    mutableListOf
                }
            }
            .flatten()
            .filter { it.index == 19 || (it.index - 19) % 40 == 0 }
            .map { it.registerValue * (it.index + 1) }
            .sum()

    }

}