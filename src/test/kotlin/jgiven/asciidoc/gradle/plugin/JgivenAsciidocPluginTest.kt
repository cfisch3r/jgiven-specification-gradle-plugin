package jgiven.asciidoc.gradle.plugin

import org.assertj.core.api.Assertions.assertThat
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path

class JgivenAsciidocPluginTest {

    val PLUGIN_ID = "jgiven-asciidoc"
    val PLUGIN_TASK = "jgivenSpecReport"
    val BUILd_FILE = "build.gradle.kts"

    @Test
    internal fun `should be loadable via plugin id` () {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply(PLUGIN_ID)
        assertThat(project.tasks.findByPath(PLUGIN_TASK)).isNotNull()
    }

    @Test
    internal fun `should generate Specification from Jgiven Report` (@TempDir projectDirectory: Path) {
        val buildConfiguration = """
            plugins {
              id("$PLUGIN_ID")
            }
        """.trimIndent()

        File(projectDirectory.toFile(),BUILd_FILE).writeText(buildConfiguration)

        val result = gradleRunner(projectDirectory).build()

        assertThat(result.task(":" + PLUGIN_TASK)?.outcome).isEqualTo(TaskOutcome.UP_TO_DATE)
    }

    private fun gradleRunner(projectDirectory: Path): GradleRunner {
        return GradleRunner.create()
                .withProjectDir(projectDirectory.toFile())
                .withPluginClasspath()
                .withArguments(PLUGIN_TASK)
    }
}