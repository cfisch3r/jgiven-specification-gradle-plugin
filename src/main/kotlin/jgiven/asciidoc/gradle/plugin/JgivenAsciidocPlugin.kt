package jgiven.asciidoc.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class JgivenAsciidocPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.task("jgivenSpecReport") {
            val jgivenResultFile = File(project.projectDir, "jgiven-reports/result.json")
            if (jgivenResultFile.exists()) {
                var text = jgivenResultFile.readText()
                File(project.projectDir,"specification").mkdir()
                File(project.projectDir,"specification/spec.pdf").writeText(text)
            }
        }
    }
}