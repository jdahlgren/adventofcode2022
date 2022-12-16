package se.johannesdahlgren.aoc22.util

import kotlin.math.abs

class Day15(private val fileName: String) {

    private val xyRegex = ".* x=(-?\\d+), y=(-?\\d+)".toRegex()

    data class Sensor(val pos: Pos, val closestBeaconPos: Pos) {
        fun getXMax(): Int {
            return listOf(pos, closestBeaconPos).maxOf { it.x }
        }

        fun getXMin(): Int {
            return listOf(pos, closestBeaconPos).minOf { it.x }
        }

        fun getYMax(): Int {
            return listOf(pos, closestBeaconPos).maxOf { it.y }
        }

        fun getYMin(): Int {
            return listOf(pos, closestBeaconPos).minOf { it.y }
        }
    }

    fun numberOfPosCantContainBeacon(targetRow: Int): Int {
        val sensors = FileReader.readFileLinesAsString(fileName)
            .map { createSensor(it) }
        val beacons = sensors.map { it.closestBeaconPos }.toSet()

//        val temp = getPositionsWithSameOrLowerDistanceAsClosestBeacon(sensors[6])
//        printMap(sensors, temp)

        return sensors
            .asSequence()
            .map { getPositionsWhereBeaconCanNotBe(it, targetRow) }
            .flatten()
            .distinct()
            .filterNot { beacons.contains(it) }
            .count()

    }

    private fun createSensor(line: String): Sensor {
        val lineSplit = line.split(":")
        val sensorPos = xyRegex.find(lineSplit.first())
        val closestBeaconPos = xyRegex.find(lineSplit.last())

        return Sensor(
            Pos(sensorPos!!.groupValues[1].toInt(), sensorPos.groupValues[2].toInt()),
            Pos(closestBeaconPos!!.groupValues[1].toInt(), closestBeaconPos.groupValues[2].toInt())
        )
    }

    private fun getPositionsWhereBeaconCanNotBe(sensor: Sensor, targetRow: Int): List<Pos> {
        val manhattanDistance = manhattanDistance(sensor)
        val beaconNotHere = mutableListOf<Pos>()

        if (abs(sensor.pos.y - manhattanDistance) <= targetRow) {
            val width = manhattanDistance - abs(sensor.pos.y - targetRow)
            (sensor.pos.x - width..sensor.pos.x + width)
                .forEach { beaconNotHere.add(Pos(it, targetRow)) }
        }
        return beaconNotHere
    }

    private fun manhattanDistance(sensor: Sensor): Int {
        return abs(sensor.pos.x - sensor.closestBeaconPos.x) + abs(sensor.pos.y - sensor.closestBeaconPos.y)
    }

    private fun printMap(sensors: List<Sensor>, temp: List<Pos>) {
        val sensorPos = sensors.map { it.pos }
        val beaconPos = sensors.map { it.closestBeaconPos }

        for (y in getYMin(sensors) - 2..getYMax(sensors) + 2) {
            for (x in getXMin(sensors) - 2..getXMax(sensors) + 2) {
                val pos = Pos(x, y)
                if (sensorPos.contains(pos)) {
                    print("S")
                } else if (beaconPos.contains(pos)) {
                    print("B")
                } else if (temp.contains(pos)) {
                    print("#")
                } else {
                    print(".")
                }
            }
            println()
        }
        println()
    }

    private fun getXMax(sensors: List<Sensor>): Int {
        return sensors.maxOf { it.getXMax() }
    }

    private fun getXMin(sensors: List<Sensor>): Int {
        return sensors.minOf { it.getXMin() }
    }

    private fun getYMax(sensors: List<Sensor>): Int {
        return sensors.maxOf { it.getYMax() }
    }

    private fun getYMin(sensors: List<Sensor>): Int {
        return sensors.minOf { it.getYMin() }
    }
}