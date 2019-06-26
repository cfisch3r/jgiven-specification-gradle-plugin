package de.agiledojo.jgivenspec

import java.nio.file.Path

class FileWriter(val filePath: Path) : Writer {
    override fun write(content: String) {
        val parentDir = filePath.toFile().parentFile
        if (!parentDir.exists())
            parentDir.mkdirs()
        filePath.toFile().writeText(content)
    }
}