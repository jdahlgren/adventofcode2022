package se.johannesdahlgren

import se.johannesdahlgren.util.FileReader

class Day2(private val filename: String) {

    enum class Result {
        WIN,
        LOSE,
        DRAW;

        fun getMove(move: Move): Move {
            return when (this) {
                WIN -> move.looses()
                LOSE -> move.beats()
                DRAW -> move
            }
        }

        companion object {
            fun getResult(result: String): Result {
                return when (result) {
                    "X" -> LOSE
                    "Y" -> DRAW
                    "Z" -> WIN
                    else -> {
                        throw RuntimeException("Bad win input")
                    }
                }
            }
        }
    }

    enum class Move {
        ROCK,
        PAPER,
        SCISSORS;

        companion object {
            fun getMove(move: String): Move {
                return when (move) {
                    "A", "X" -> ROCK
                    "B", "Y" -> PAPER
                    "C", "Z" -> SCISSORS
                    else -> {
                        throw RuntimeException("Bad move input")
                    }
                }
            }
        }

        fun beats(): Move {
            return when (this) {
                ROCK -> SCISSORS
                PAPER -> ROCK
                SCISSORS -> PAPER
            }
        }

        fun looses(): Move {
            return when (this) {
                ROCK -> PAPER
                PAPER -> SCISSORS
                SCISSORS -> ROCK
            }
        }

    }

    data class Round(val myMove: Move, val opponentMove: Move) {
        fun getWinnerScore(): Int {
            return if (myMove.beats() == opponentMove) {
                6
            } else if (opponentMove.beats() == myMove) {
                0
            } else {
                3
            }
        }
    }

    fun getTotalScore(): Int {
        return FileReader.readFileLinesAsString(filename)
            .map { it.split(" ") }
            .map { Round(Move.getMove(it.last()), Move.getMove(it.first())) }
            .sumOf { calculateScore(it) }
    }

    fun getTotalScoreFollowGuide(): Int {
        return FileReader.readFileLinesAsString(filename)
            .map { it.split(" ") }
            .map {
                Round(
                    Result.getResult(it.last())
                        .getMove(Move.getMove(it.first())), Move.getMove(it.first())
                )
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