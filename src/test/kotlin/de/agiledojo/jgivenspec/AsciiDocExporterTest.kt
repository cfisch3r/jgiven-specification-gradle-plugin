package de.agiledojo.jgivenspec

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.contains
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class AsciiDocExporterTest {

    @Mock
    private lateinit var writer: Writer;

    @Test
    internal fun shouldExportSpecificationAsHTML() {
        val exporter = AsciiDocExporter(writer)
        exporter.addTemplate("== {{name}}")
        val specification = Specification("Spec 1")
        exporter.toHtml(specification)
        verify(writer).write(ArgumentMatchers.contains("<h2 id=\"_spec_1\">Spec 1</h2>"))
    }
}