package se.johannesdahlgren.aoc22

import se.johannesdahlgren.aoc22.util.FileReader

class Day7(private val fileName: String) {
    private val commandStart = "$"
    private val cd = "cd"
    private val ls = "ls"
    private val dir = "dir"
    private val cdCommandLength = commandStart.length + cd.length + 2
    private val fileRegex = Regex("(\\d+) (.+)")
    private val fileSystemSize = 70000000

    data class Directory(
        val name: String,
        val directories: MutableList<Directory>,
        val files: MutableList<File>,
        val parent: Directory?
    )

    data class File(val name: String, val size: Long)

    fun calcTotalSize(folderSizeAtMost: Long): Long {
        val terminalOutput = FileReader.readFileLinesAsString(fileName)
        val root = setupFileSystem(terminalOutput)
        return getAllFolders(root)
            .filter { getFolderSize(it) <= folderSizeAtMost }
            .sumOf { getFolderSize(it) }
    }

    fun findSmallestFolder(sizeNeededForUpdate: Long): Long {
        val terminalOutput = FileReader.readFileLinesAsString(fileName)
        val root = setupFileSystem(terminalOutput)
        val unusedSpace = fileSystemSize - getFolderSize(root)
        val spaceToClear = sizeNeededForUpdate - unusedSpace

        return getAllFolders(root)
            .filter { getFolderSize(it) >= spaceToClear }
            .minOf { getFolderSize(it) }
    }

    private fun getAllFolders(directory: Directory): List<Directory> {
        return directory.directories + directory.directories.flatMap { getAllFolders(it) }
    }

    private fun getFolderSize(directory: Directory): Long {
        return directory.files.sumOf { it.size } + directory.directories.sumOf { getFolderSize(it) }
    }

    private fun setupFileSystem(terminalOutput: List<String>): Directory {
        val firstLine = terminalOutput.first()
        val rootFolderName = firstLine.subSequence(cdCommandLength, firstLine.length).toString()
        val rootFolder = Directory(rootFolderName, mutableListOf(), mutableListOf(), null)

        setupDirectory(terminalOutput.drop(1), rootFolder)

        return rootFolder
    }

    private fun setupDirectory(remainingTerminalOutput: List<String>, parentFolder: Directory) {
        var nextFolder = parentFolder
        var nexLinesToProcess: List<String>
        val nextLine = remainingTerminalOutput.first()
        if (nextLine.startsWith("$commandStart $ls")) {
            nexLinesToProcess = remainingTerminalOutput.drop(1)
            val linesProcessed = createSubDirectory(nexLinesToProcess, parentFolder)
            nexLinesToProcess = nexLinesToProcess.drop(linesProcessed)
        } else {
            nextFolder = if (nextLine == "$commandStart $cd ..") {
                parentFolder.parent!!
            } else {
                val nextDirName = nextLine.subSequence(cdCommandLength, nextLine.length)
                parentFolder.directories.first { it.name == nextDirName }
            }
            nexLinesToProcess = remainingTerminalOutput.drop(1)
        }

        if (nexLinesToProcess.isNotEmpty()) {
            setupDirectory(nexLinesToProcess, nextFolder)
        }
    }

    private fun createSubDirectory(remainingTerminalOutput: List<String>, parentFolder: Directory): Int {
        val dirContent = remainingTerminalOutput.takeWhile { !it.startsWith(commandStart) }
        for (line in dirContent) {
            if (line.startsWith(dir)) {
                val subDir = newDir(line, parentFolder)
                parentFolder.directories.add(subDir)
            } else {
                val file = newFile(line)
                parentFolder.files.add(file)
            }
        }
        return dirContent.size
    }

    private fun newDir(line: String, parentFolder: Directory): Directory {
        val folderName = line.subSequence(dir.length + 1, line.length).toString()
        return Directory(folderName, mutableListOf(), mutableListOf(), parentFolder)
    }

    private fun newFile(line: String): File {
        val split = fileRegex.find(line)!!.groupValues
        return File(split[2], split[1].toLong())
    }
}