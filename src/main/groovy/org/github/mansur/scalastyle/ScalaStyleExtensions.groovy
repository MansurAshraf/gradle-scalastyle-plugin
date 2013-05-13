package org.github.mansur.scalastyle

import org.gradle.api.Task

/**
 * @author Muhammad Ashraf
 * @since 5/11/13
 */
class ScalaStyleExtensions {

    String configLocation
    String outputFile
    String outputEncoding
    Boolean failOnViolation = true
    Boolean failOnWarning = false
    Boolean skip = false
    Boolean verbose = false
    Boolean quiet = false
    Boolean includeTestSourceDirectory=false
    String buildDirectory
    String baseDirectory
    String inputEncoding

}
