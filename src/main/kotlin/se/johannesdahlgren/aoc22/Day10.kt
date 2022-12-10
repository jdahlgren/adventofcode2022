package se.johannesdahlgren.aoc22

import se.johannesdahlgren.aoc22.util.FileReader

class Day10(private val fileName: String) {
    data class Cycle(val index: Int, val registerValue: Int)

    fun getSignalStrength(): Int {
        return readInstructions()
            .filter { it.index == 19 || (it.index - 19) % 40 == 0 }
            .map { it.registerValue * (it.index + 1) }
            .sum()
    }

    fun drawCRTScreen(): String {
        val litPixel = '#'
        val darkPixel = '.'
        val crtRow = darkPixel.toString().padEnd(40, darkPixel).toCharArray()
        val crtScreen = Array(6) { crtRow.clone() }
        var registerValue = 1
        readInstructions().chunked(40)
            .forEachIndexed { row, cycles ->
                cycles.forEachIndexed { col, cycle ->
                    if (col in registerValue - 1..registerValue + 1) {
                        crtScreen[row][col] = litPixel
                    }
                    registerValue = cycle.registerValue
                }
            }
        return crtScreen.joinToString(separator = "\n") { it.joinToString(separator = "") }
    }

    private fun readInstructions(): Sequence<Cycle> {
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
    }

}