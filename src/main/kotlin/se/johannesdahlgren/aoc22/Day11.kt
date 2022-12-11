package se.johannesdahlgren.aoc22

import se.johannesdahlgren.aoc22.util.FileReader

class Day11(private val fileName: String) {
    fun monkeyBusiness(): Long {
        val monkeys = FileReader.readFileAsStringSplitEmptyLine(fileName)
            .map { createMonkey(it) }

        for (i in 0 until 20) {
            doRound(monkeys)
        }

        return monkeys
            .map { it.numberOfInspections }
            .sortedDescending()
            .take(2)
            .reduce { a, b -> a * b }
    }

    private fun doRound(monkeys: List<Monkey>) {
        monkeys.forEach { it.doTurn(monkeys) }
    }

    private fun createMonkey(monkeyInput: String): Monkey {
        val startingItems =
            monkeyInput.lines().drop(1).first().split(": ").last().split(", ").map { it.toLong() }.toMutableList()
        val operation = monkeyInput.lines().drop(2).first().split("old ").last()
        val test = monkeyInput.lines().drop(3).first().split("by ").last().toInt()
        val testTrue = monkeyInput.lines().drop(4).first().split("monkey ").last().toInt()
        val testFalse = monkeyInput.lines().drop(5).first().split("monkey ").last().toInt()
        return Monkey(startingItems, operation, test, testTrue, testFalse, 0)
    }

    data class Monkey(
        private var itemWorryLevel: MutableList<Long>,
        private val operation: String,
        private val test: Int,
        private val testTrue: Int,
        private val testFalse: Int,
        var numberOfInspections: Long
    ) {
        private fun doOperation(oldValue: Long): Long {
            val op = operation.split(" ")
            val opValue = if (op.last() == "old") oldValue else op.last().toLong()
            return when (op.first()) {
                "*" -> oldValue * opValue
                "+" -> oldValue + opValue
                else -> 0
            }
        }

        private fun test(worryLevel: Long): Int {
            return if (worryLevel % test == 0L) {
                testTrue
            } else {
                testFalse
            }
        }

        fun doTurn(monkeys: List<Monkey>) {
            itemWorryLevel.forEach {
                val newWorryLevel = doOperation(it) / 3
                val newMonkey = test(newWorryLevel)
                itemWorryLevel = itemWorryLevel.drop(1).toMutableList()
                monkeys[newMonkey].itemWorryLevel.add(newWorryLevel)
                numberOfInspections++
            }
        }
    }
}