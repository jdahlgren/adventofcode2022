package se.johannesdahlgren.aoc22.util

class FileReader {
    companion object {

        fun readFileAsStringSplitEmptyLine(fileName: String): List<String> {
            val filePath = "/$fileName.txt"
            return FileReader::class.java.getResource(filePath)!!.readText()
                .split("\r\n\r\n")
        }

        fun readFileLinesAsString(fileName: String): List<String> {
            val filePath = "/$fileName.txt"
            return FileReader::class.java.getResource(filePath)!!.readText()
                .lines()
        }

        fun readFileAsString(fileName: String): String {
            val filePath = "/$fileName.txt"
            return FileReader::class.java.getResource(filePath)!!.readText()
                .lines()
                .first()
        }
    }
}