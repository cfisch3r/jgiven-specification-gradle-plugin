package jgiven.asciidoc.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class JgivenAsciidocPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.task("jgivenSpecReport")
    }
}