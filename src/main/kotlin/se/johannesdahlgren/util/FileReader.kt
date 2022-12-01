package se.johannesdahlgren.util

class FileReader {
    companion object {

        fun readFileAsStringList(fileName: String): List<String> {
            val filePath = "/$fileName.txt"
            println(filePath)
            return FileReader::class.java.getResource(filePath)!!.readText()
                .lines()
        }
    }
}