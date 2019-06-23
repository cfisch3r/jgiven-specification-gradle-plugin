package jgiven.asciidoc.gradle.plugin

import org.assertj.core.api.Assertions.assertThat
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Test

class JgivenAsciidocPluginTest {

    val PLUGIN_ID: String = "jgiven-asciidoc"
    val PLUGIN_TASK: String = "jgivenSpecReport"

    @Test
    internal fun `should be loadable via plugin id` () {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply(PLUGIN_ID)
        assertThat(project.tasks.findByPath(PLUGIN_TASK)).isNotNull()
    }
}