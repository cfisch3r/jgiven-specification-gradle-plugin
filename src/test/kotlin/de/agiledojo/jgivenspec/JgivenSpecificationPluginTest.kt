package de.agiledojo.jgivenspec

import org.assertj.core.api.Assertions.assertThat
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path

class JgivenSpecificationPluginTest {

    val PLUGIN_ID = "jgiven-specification"
    val PLUGIN_TASK = "jgivenSpecReport"
    val BUILD_FILE = "build.gradle.kts"
    val JGIVEN_REPORTS_DIR = "jgiven-reports"

    @Test
    internal fun `should be loadable via plugin id` () {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply(PLUGIN_ID)
        assertThat(project.tasks.findByPath(PLUGIN_TASK)).isNotNull()
    }

    @Test
    internal fun `should generate Specification from Jgiven Report` (@TempDir projectDirectory: Path) {

        createBuildFile(projectDirectory, """
            plugins {
              id("$PLUGIN_ID")
            }
        """.trimIndent())

        addJgivenResultToProjectDir(projectDirectory, "jgiven/reports/result.json")

        val result = runBuild(projectDirectory)

        assertThat(result.task(":$PLUGIN_TASK")?.outcome).isEqualTo(TaskOutcome.UP_TO_DATE)
        assertThat(projectDirectory.resolve("specification/spec.html").toFile()).exists()
    }

    private fun runBuild(projectDirectory: Path) = gradleRunner(projectDirectory).build()

    private fun addJgivenResultToProjectDir(projectDirectory: Path, resultResource: String) {
        File(projectDirectory.toFile(), JGIVEN_REPORTS_DIR).mkdir()
        val text = this.javaClass.classLoader.getResource(resultResource).readText()
        File(projectDirectory.toFile(), "$JGIVEN_REPORTS_DIR/result.json").writeText(text)
    }

    private fun createBuildFile(projectDirectory: Path, buildConfiguration: String) {
        File(projectDirectory.toFile(), BUILD_FILE).writeText(buildConfiguration)
    }

    private fun gradleRunner(projectDirectory: Path): GradleRunner {
        return GradleRunner.create()
                .withProjectDir(projectDirectory.toFile())
                .withPluginClasspath()
                .withArguments(PLUGIN_TASK)
    }
}