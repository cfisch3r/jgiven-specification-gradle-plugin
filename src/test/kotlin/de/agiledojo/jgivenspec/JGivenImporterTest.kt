package de.agiledojo.jgivenspec

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.io.TempDir
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.io.File
import java.nio.file.Path
import java.util.*

@ExtendWith(MockitoExtension::class)
class JGivenImporterTest {

    @Mock
    private lateinit var exporter: SpecExporter

    @Test
    internal fun readsFeatureFromReport(@TempDir tempDir: Path) {
        val reportsDir = createReportDir(tempDir)
        addRessourceToDir(reportsDir, "de.agiledojo.jgivenspec.samples.SimpleFeatureTest.json")
        JGivenImporter(exporter).read(reportsDir)
        verify(exporter).toHtml(Arrays.asList(Feature("Simple Feature")))
    }

    private fun addRessourceToDir(reportsDir: File, ressource: String) {
        val report = File(this.javaClass.classLoader.getResource("jgiven/reports/$ressource").file)
        report.copyTo(File(reportsDir, report.name))
    }

    private fun createReportDir(tempDir: Path): File {
        val reportsDir = File(tempDir.toFile(), "jgiven-reports")
        reportsDir.mkdir()
        return reportsDir
    }
}