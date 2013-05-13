package org.github.mansur.scalastyle

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Ignore

import static org.junit.Assert.assertTrue

/**
 * @author Muhammad Ashraf
 * @since 5/12/13
 */
class ScalaStylePluginTest {


    @Ignore
    def void testPlugin() {
        Project project = ProjectBuilder.builder().build()
        project.apply plugin: 'scalaStyle'
        project.scalaStyle.configLocation="Hello World"
        def scalaStyle = project.tasks.scalaStyle
        assertTrue(scalaStyle instanceof ScalaStyleTask)
        scalaStyle.execute()

    }
}
