package de.agiledojo.jgivenspec

import org.asciidoctor.Asciidoctor
import org.jtwig.JtwigModel
import org.jtwig.JtwigTemplate

class AsciiDocExporter(val writer: Writer) {

    var template = ""

    fun toHtml(specification: Specification) {
        val jtwigTemplate = JtwigTemplate.inlineTemplate(template)
        val model = JtwigModel.newModel().with("name", specification.name)
        val adoc = jtwigTemplate.render(model)
        val asciidoctor = Asciidoctor.Factory.create()
        val html = asciidoctor.convert( adoc, HashMap<String, Object>() as Map<String, Any>?)
        writer.write(html)
    }

    fun addTemplate(template: String) {
        this.template = template
    }

}
