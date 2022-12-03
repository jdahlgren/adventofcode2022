package se.johannesdahlgren.aoc22

import se.johannesdahlgren.aoc22.util.FileReader

class Day2(private val filename: String) {
    private val desiredResult = mapOf(Pair("X", Result.LOSE), Pair("Y", Result.DRAW), Pair("Z", Result.WIN))
    private val move = mapOf(
        Pair("X", Move.ROCK), Pair("Y", Move.PAPER), Pair("Z", Move.SCISSORS),
        Pair("A", Move.ROCK), Pair("B", Move.PAPER), Pair("C", Move.SCISSORS)
    )

    enum class Result {
        WIN,
        LOSE,
        DRAW;

        fun useMoveToBeat(move: Move): Move {
            return when (this) {
                WIN -> move.loosesTo()
                LOSE -> move.beats()
                DRAW -> move
            }
        }
    }

    enum class Move {
        ROCK,
        PAPER,
        SCISSORS;

        fun beats(): Move {
            return when (this) {
                ROCK -> SCISSORS
                PAPER -> ROCK
                SCISSORS -> PAPER
            }
        }

        fun loosesTo(): Move {
            return when (this) {
                ROCK -> PAPER
                PAPER -> SCISSORS
                SCISSORS -> ROCK
            }
        }

    }

    data class Round(val myMove: Move, val opponentMove: Move) {
        fun getWinnerScore(): Int {
            return when (opponentMove) {
                myMove.beats() -> 6
                myMove.loosesTo() -> 0
                else -> 3
            }
        }
    }

    fun getTotalScore(): Int {
        return FileReader.readFileLinesAsString(filename)
            .map { it.split(" ") }
            .map { Round(move[it.last()]!!, move[it.first()]!!) }
            .sumOf { calculateScore(it) }
    }

    fun getTotalScoreFollowGuide(): Int {
        return FileReader.readFileLinesAsString(filename)
            .map { it.split(" ") }
            .map {
                val opponentMove = move[it.first()]!!
                val myMove = desiredResult[it.last()]!!.useMoveToBeat(opponentMove)
                Round(myMove, opponentMove)
            }
            .sumOf { calculateScore(it) }
    }

    private fun calculateScore(round: Round): Int {
        return when (round.myMove) {
            Move.ROCK -> 1
            Move.PAPER -> 2
            Move.SCISSORS -> 3
        } + round.getWinnerScore()
    }
}