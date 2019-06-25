package de.agiledojo.jgivenspec

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import java.io.FileWriter

class JgivenSpecificationPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.task("jgivenSpecReport") {

            val reportsDir = File(project.projectDir, "jgiven-reports")
            if (reportsDir.exists()) {
                val importer = JGivenImporter(AsciiDocExporter(object : Writer {
                    override fun write(content: String) {
                        val specFile = File(project.projectDir, "spec.html")
                        FileWriter(specFile).write(content)
                    }
                }))
                importer.read(reportsDir)
            }
        }
    }
}