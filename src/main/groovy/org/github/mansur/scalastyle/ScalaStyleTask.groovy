/*
 * Copyright 2013. Muhammad Ashraf
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.github.mansur.scalastyle

import org.gradle.api.file.FileTree
import org.gradle.api.tasks.SourceTask
import org.gradle.api.tasks.TaskAction
import org.scalastyle.ScalastyleChecker
import org.scalastyle.ScalastyleConfiguration
import org.scalastyle.TextOutput
import org.scalastyle.XmlOutput
import com.typesafe.config.ConfigFactory
import com.typesafe.config.Config

/**
 * @author Muhammad Ashraf
 * @since 5/11/13
 */
class ScalaStyleTask extends SourceTask {
    File buildDirectory
    String configLocation
    String outputFile
    String outputEncoding = "UTF-8"
    Boolean failOnViolation = true
    Boolean failOnWarning = false
    Boolean skip = false
    Boolean verbose = false
    Boolean quiet = true
    Boolean includeTestSourceDirectory = false
    String inputEncoding = "UTF-8"
    ScalaStyleUtils scalaStyleUtils = new ScalaStyleUtils()
    String testSource
    FileTree testSourceDir

    ScalaStyleTask() {
        super()
        setDescription("Scalastyle examines your Scala code and indicates potential problems with it.")
    }

    @TaskAction
    def scalaStyle() {
        extractAndValidateProperties()
        if (!skip) {
            try {
                def startMs = System.currentTimeMillis()
                def configuration = ScalastyleConfiguration.readFromXml(configLocation)
                def fileToProcess = scalaStyleUtils.getFilesToProcess(source.getFiles().toList(), testSourceDir.getFiles().toList(), inputEncoding, includeTestSourceDirectory)
                def messages = new ScalastyleChecker().checkFiles(configuration, fileToProcess)
                def config = ConfigFactory.load().copy()

                def outputResult = new TextOutput(config, verbose, quiet).output(messages)

                getLogger().debug("Saving to outputFile={}", project.file(outputFile).getCanonicalPath());
                XmlOutput.save(config, outputFile, outputEncoding, messages)

                def stopMs = System.currentTimeMillis()
                if (!quiet) {
                    getLogger().info("Processed {} file(s)", outputResult.files())
                    getLogger().info("Found {} warnings", outputResult.warnings())
                    getLogger().info("Found {} errors", outputResult.errors())
                    getLogger().info("Finished in {} ms", stopMs - startMs)
                }

                def violations = outputResult.errors() + ((failOnWarning) ? outputResult.warnings() : 0)

                processViolations(violations)
            } catch (Exception e) {
                throw new Exception("Scala check error", e)
            }
        } else {
            getLogger().info("Skipping Scalastyle")
        }
    }

    private void processViolations(int violations) {
        if (violations > 0) {
            if (failOnViolation) {
                throw new Exception("You have " + violations + " Scalastyle violation(s).")
            } else {
                project.getLogger().warn("Scalastyle:check violations detected but failOnViolation set to " + failOnViolation)
            }
        } else {
            project.getLogger().debug("Scalastyle:check no violations found")
        }
    }

    private void extractAndValidateProperties() {
        if (configLocation == null) {
            throw new Exception("No Scalastyle configuration file provided")
        }

        if (source == null) {
            throw new Exception("Specify Scala source set")
        }

        if (includeTestSourceDirectory && testSource == null) {
            testSourceDir = project.fileTree(project.projectDir.absolutePath + "/src/test/scala")
        } else {
            testSourceDir = project.fileTree(project.projectDir.absolutePath + "/" + testSource)
        }

        if (!new File(configLocation).exists()) {
            throw new Exception("configLocation " + configLocation + " does not exist")
        }


        if (buildDirectory == null) {
            buildDirectory = project.buildDir
        }

        if (outputFile == null) {
            outputFile = buildDirectory.absolutePath + "/scala_style_result.xml"
        }

        if (!skip && !project.file(outputFile).exists()) {
            project.file(outputFile).getParentFile().mkdirs()
            project.file(outputFile).createNewFile()
        }

        if (verbose) {
            getLogger().info("configLocation: {}", configLocation)
            getLogger().info("buildDirectory: {}", buildDirectory)
            getLogger().info("outputFile: {}", outputFile)
            getLogger().info("outputEncoding: {}", outputEncoding)
            getLogger().info("failOnViolation: {}", failOnViolation)
            getLogger().info("failOnWarning: {}", failOnWarning)
            getLogger().info("verbose: {}", verbose)
            getLogger().info("quiet: {}", quiet)
            getLogger().info("skip: {}", skip)
            getLogger().info("includeTestSourceDirectory: {}", includeTestSourceDirectory)
            getLogger().info("inputEncoding: {}", inputEncoding)
        }
    }
}
