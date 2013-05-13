package org.github.mansur.scalastyle

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author Muhammad Ashraf
 * @since 5/11/13
 */
class ScalaStylePlugin implements Plugin<Project> {


    void apply(Project project) {
        project.extensions.create("scalaStyle", ScalaStyleExtensions)
        project.configurations.add("scalaStyle")
                .setVisible(false)
                .setTransitive(true)
                .setDescription('Scala Style libraries to be used for this project.')

        project.task(type: ScalaStyleTask, 'scalaStyleTask')

        def compileScala = project.tasks["compileScala"]
        compileScala.dependsOn project.tasks.withType(ScalaStyleTask)


        project.afterEvaluate {
            project.tasks.scalaStyleTask.execute()
        }
    }

}
