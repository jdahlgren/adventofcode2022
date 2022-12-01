package se.johannesdahlgren.util

class FileReader {
    companion object {

        fun readFileAsStringSplitEmptyLine(fileName: String): List<String> {
            val filePath = "/$fileName.txt"
            return FileReader::class.java.getResource(filePath)!!.readText()
                    .split("\r\n\r\n")
        }
    }
}