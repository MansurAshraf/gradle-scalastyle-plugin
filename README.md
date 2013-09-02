# Scala Style Gradle Plugin

### Instruction

```
maven repo: http://repository-uncommon-configuration.forge.cloudbees.com/release/
groupId: org.github.mansur.oozie
artifactId: gradle-scalastyle-plugin_2.9.2 OR gradle-scalastyle-plugin_2.10
version: 0.2
```

```groovy
  apply plugin: 'scalaStyle'
```

Add following dependencies to your buildScript

```groovy
     classpath "org.scala-lang:scala-library:2.10"
     classpath "org.github.mansur.scalastyle:gradle-scalastyle-plugin_2.10:0.2"
     classpath "org.scalastyle:scalastyle_2.10:0.3.2"
```

Configure the plugin

```groovy
  scalaStyle {
      configLocation = "/path/to/scalaStyle.xml"
      includeTestSourceDirectory = true
      source = "src/main/scala"
      testSource = "src/test/scala"
  }

```

Other optional properties are

```groovy
        outputFile  //Default => $buildDir/scala_style_result.xml
        outputEncoding //Default => UTF-8
        failOnViolation //Default => true
        failOnWarning //Default => false
        skip  //Default => false
        verbose //Default => false
        quiet //Default => false
        includeTestSourceDirectory //Default => false
        inputEncoding //Default => UTF-8
```