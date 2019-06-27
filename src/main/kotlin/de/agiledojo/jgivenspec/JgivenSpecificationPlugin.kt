package de.agiledojo.jgivenspec

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class JgivenSpecificationPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.task("jgivenSpecReport") {

            val reportsDir = File(project.projectDir, "jgiven-reports")
            if (reportsDir.exists()) {
                val importer = JGivenImporter(AsciiDocExporter(FileWriter(specFile(project.projectDir))))
                importer.read(reportsDir.toPath())
            }
        }
    }

    private fun specFile(reportsDir: File) = reportsDir.toPath()
            .resolve("specification/spec.html")
}