package de.agiledojo.jgivenspec

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class AsciiDocExporterTest {

    @Mock
    private lateinit var writer: Writer;

    @Test
    internal fun shouldExportAsHTML() {
        val exporter = AsciiDocExporter(writer)
        exporter.addTemplate("== {{name}}")
        val specification = Specification("Spec 1")
        exporter.toHtml(specification)
        verify(writer).write("<div class=\"sect1\">\n" +
                "<h2 id=\"_spec_1\">Spec 1</h2>\n" +
                "<div class=\"sectionbody\">\n" +
                "\n" +
                "</div>\n" +
                "</div>")
    }
}