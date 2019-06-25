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
    internal fun readsSpecificationFromReport(@TempDir directory: Path) {
        val reportsDir = File(directory.toFile(), "jgiven-reports")
        reportsDir.mkdir()
        val report = File(this.javaClass.classLoader.getResource("jgiven/reports/de.agiledojo.jgivenspec.samples.SimpleFeatureTest.json").file)
        report.copyTo(File(reportsDir,report.name))
        JGivenImporter(exporter).read(reportsDir)
        verify(exporter).toHtml(Arrays.asList(Specification("Simple Feature")))
    }
}