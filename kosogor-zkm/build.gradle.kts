import tanvd.kosogor.proxy.publishJar
import tanvd.kosogor.proxy.publishPlugin

group = "tanvd.kosogor.zkm"
version = "1.0.0-SNAPSHOT"

dependencies {
    compile(gradleKotlinDsl())
    compile(gradleApi())
}

publishPlugin {
    id = "tanvd.kosogor.zkm"
    displayName = "kosogor-zkm"
    implementationClass = "tanvd.kosogor.zkm.KosogorZkmPlugin"
    version = project.version.toString()

    info {
        website = "https://github.com/TanVD/kosogor"
        vcsUrl = "https://github.com/TanVD/kosogor"
        description = "ZKM wrapper task for Gradle"
        tags.addAll(listOf("zkm", "obfuscate", "kotlin"))
    }
}

publishJar {
    publication {
        artifactId = "tanvd.kosogor.zkm.gradle.plugin"
    }

    bintray {
        username = "tanvd"
        repository = "tanvd.kosogor"
        secretKey = project.findProperty("bintray_key") as String? ?: ""
        info {
            description = "Kosogor ZKM plugin artifact"
            githubRepo = "https://github.com/TanVD/kosogor"
            vcsUrl = "https://github.com/TanVD/kosogor"
            labels.addAll(listOf("zkm", "gradle", "kotlin-dsl", "plugin"))
        }
    }
}
