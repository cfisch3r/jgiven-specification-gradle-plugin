package de.agiledojo.jgivenspec

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path

class FileWriterTest {

    @Test
    internal fun writesContentInNewFile(@TempDir tempDir: Path) {
        val filePath = tempDir.resolve("spec/out/out.html")
        FileWriter(filePath).write("abc")
        val text = filePath.toFile().readText()
        assertThat(text).isEqualTo("abc")
    }

    @Test
    internal fun overwritesExistingFileWithContent(@TempDir tempDir: Path) {
        val filePath = tempDir.resolve("out.html")
        filePath.toFile().createNewFile()
        FileWriter(filePath).write("abc")
        val text = filePath.toFile().readText()
        assertThat(text).isEqualTo("abc")
    }
}