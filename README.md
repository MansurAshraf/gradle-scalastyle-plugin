# Scala Style Gradle Plugin

### Instruction

```groovy
  apply plugin: 'scalaStyle'
```

Add following dependencies to your buildScript

```groovy
     classpath "org.scala-lang:scala-library:2.9.2"
     classpath "org.github.mansur.scalastyle:gradle-scalastyle-plugin_2.9.2:0.1"
     classpath "org.scalastyle:scalastyle_2.9.2:0.2.0"
```

Configure the plugin

```groovy
  scalaStyle {
      configLocation = "/path/to/scalaStye.xml"
      includeTestSourceDirectory = true
  }
  scalaStyleTask.source = "src/main/scala"
  scalaStyleTask.testSource = "src/test/scala"
```

Other optional properties are

```groovy
        outputFile  #Default => $buildDir/scala_style_result.xml
        outputEncoding #Default => UTF-8
        failOnViolation #Default => true
        failOnWarning #Default => false
        skip  #Default => false
        verbose #Default => false
        quiet #Default => false
        includeTestSourceDirectory #Default => false
        inputEncoding #Default => UTF-8
```