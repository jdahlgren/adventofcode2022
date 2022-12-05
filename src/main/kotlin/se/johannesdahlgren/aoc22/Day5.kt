package se.johannesdahlgren.aoc22

import se.johannesdahlgren.aoc22.util.FileReader

class Day5(private val fileName: String) {
    private val procedureRegex = ".* (\\d+).* (\\d+).* (\\d+)".toRegex()

    data class CratesAndMovementProcedure(val crates: List<ArrayDeque<Char>>, val procedure: List<Procedure>)
    data class Procedure(val numberOfStacks: Int, val from: Int, val to: Int)

    fun getCratesOnTop(): String {
        val input = FileReader.readFileAsStringSplitEmptyLine(fileName)
        val test = toCratesAndMovementProcedure(input)
        test.procedure.forEach { moveCrate(it, test.crates) }
        return test.crates.map { it.first() }
            .joinToString(separator = "")
    }

    private fun moveCrate(procedure: Procedure, crates: List<ArrayDeque<Char>>) {
        (0 until procedure.numberOfStacks).forEach { _ ->
            makeMove(crates, procedure.from, procedure.to)
        }
    }

    private fun makeMove(crates: List<ArrayDeque<Char>>, from: Int, to: Int) {
        val crateToMove = crates[from - 1].removeFirst()
        crates[to - 1].addFirst(crateToMove)
    }

    private fun toCratesAndMovementProcedure(input: List<String>): CratesAndMovementProcedure {
        return CratesAndMovementProcedure(toCrateStacks(input.first()), toProcedure(input.last()))

    }

    private fun toCrateStacks(firstPartInput: String): List<ArrayDeque<Char>> {
        val goodInput = firstPartInput.lines()
            .dropLast(1)
            .asReversed()
            .map { it.chunked(4) { chunk -> chunk[1] } }

        val crateStacks = goodInput[0].indices
            .map { ArrayDeque<Char>() }
            .toList()

        goodInput.forEach { stack ->
            stack.forEachIndexed { j, crate ->
                if (crate.isLetter()) {
                    crateStacks[j].addFirst(crate)
                }
            }
        }
        return crateStacks
    }

    private fun toProcedure(lastPartInput: String): List<Procedure> {
        return lastPartInput.lines()
            .map { procedureRegex.find(it) }
            .map { it!!.groupValues }
            .map { Procedure(it[1].toInt(), it[2].toInt(), it[3].toInt()) }

    }
}