# kosogor-zkm

`kosogor-zkm` is a plugin which provides Kotlin-DSL facade to ZKM obfuscation.

It wraps call of `ZKM` into Gradle task and passes to its script few predefined variables from task definition.

## Setup

`kosogor-zkm` is released to `plugins.gradle.org`

To set up it just apply plugin: 

```
plugins {
    id("tanvd.kosogor.zkm") version "1.0.0" apply true
}
```
## ZKM task

ZKM task provides simple interface to ZKM from Gradle.

It passes following variables from task definition to ZKM environment:
* inputJars -- it will be `%INPUT_JARS%` variable which evaluates into `open absolutePath.jar`
* outputDir -- it will be `%OUPUT_DIR%` variable
* changeLogFile -- it will be `%CHANGELOG_FILE%` variable (can be used for `changeLogFileOut`)
* zkmLogFile -- used in call of zkm.jar as `-l zkmLogFile.absolutePath`

Also, there are few variables used by ZKM itself:
* zkmClasspath -- classpath to ZKM should use during obfuscation
* zkmJar -- path to your ZKM jar
* zkmScript -- path to your ZKM script

So, to sum up, here is the simple definition of task:

```kotlin
zkmJars {
    dependsOn(jar)

    zkmClasspath = configurations["compile"]
    
    inputJars = setOf(jar)
    outputDir = File(projectDir, "obfuscated-jar")

    changeLogFile = File(projectDir, "obfuscated-log/ChangeLog.txt")
    zkmLogFile = File(projectDir, "obfuscated-log/ZkmLog.txt")

    zkmJar = File(projectDir, "zkm/zkm.jar")
    zkmScript = File(projectDir, "zkm/script.zkm")
}
```