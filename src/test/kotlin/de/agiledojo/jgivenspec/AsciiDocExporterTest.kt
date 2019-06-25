package de.agiledojo.jgivenspec

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*


@ExtendWith(MockitoExtension::class)
class AsciiDocExporterTest {

    @Mock
    private lateinit var writer: Writer;

    @Test
    internal fun shouldExportSpecificationAsHTML() {
        val exporter = AsciiDocExporter(writer)
        val template = """
            {% for feature in features %}
            == {{feature.name}}
            {% endfor %}
        """.trimIndent()
        exporter.addTemplate(template)
        val feature = Feature("Spec 1")
        exporter.toHtml(Arrays.asList(feature))
        verify(writer).write(ArgumentMatchers.contains("<h2 id=\"_spec_1\">Spec 1</h2>"))
    }
}