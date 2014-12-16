# Scala Style Gradle Plugin

### Instructions

```
maven repo: http://dl.bintray.com/releashaus/release
groupId: org.github.ngbinh.scalastyle
artifactId:  gradle-scalastyle-plugin_2.11
version: 0.5.1
```

```groovy
  apply plugin: 'scalaStyle'
```

Add following dependencies to your buildScript

```groovy
     classpath "org.github.ngbinh.scalastyle:gradle-scalastyle-plugin_2.11:0.5.1"
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
    maven { url "http://dl.bintray.com/ngbinh/maven" }
  }

  dependencies {
    classpath 'org.github.ngbinh.scalastyle:gradle-scalastyle-plugin_2.11:0.5.1'
  }

}

scalaStyle {
  configLocation = "mega-project/sub-project/scalastyle_config.xml"
  includeTestSourceDirectory = true
  source = "src/main/scala"
  testSource = "src/test/scala"
}
```
