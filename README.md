# Scala Style Gradle Plugin

### IMPORTANT
This project is not actively maintained on anymore. Go to
 https://github.com/ngbinh/gradle-scalastyle-plugin for Gradle Scala Style plugin
###

### Instructions

```
maven repo: http://dl.bintray.com/releashaus/release
groupId: org.github.mansur.scalastyle
artifactId:  gradle-scalastyle-plugin_2.10
version: 0.5.0
```

```groovy
  apply plugin: 'scalaStyle'
```

Add following dependencies to your buildScript

```groovy
     classpath "org.github.mansur.scalastyle:gradle-scalastyle-plugin_2.10:0.5.0"
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

#### Full Buildscript Example
```groovy
apply plugin: 'scalaStyle'

buildscript {
  repositories {
    mavenLocal()
    mavenCentral()
    maven { url "http://dl.bintray.com/releashaus/release" }
  }

  dependencies {
    classpath 'org.github.mansur.scalastyle:gradle-scalastyle-plugin_2.10:0.5.0'
  }

}

scalaStyle {
  configLocation = "mega-project/sub-project/scalastyle_config.xml"
  includeTestSourceDirectory = true
  source = "src/main/scala"
  testSource = "src/test/scala"
}
```
