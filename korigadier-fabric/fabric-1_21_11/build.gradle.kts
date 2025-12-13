plugins {
    id("fabric-loom") version "1.14-SNAPSHOT"
    alias(libs.plugins.kotlin.jvm)
    id("com.vanniktech.maven.publish") version "0.35.0"
}

var mc = "1.21.11"
var mcUnderscore = mc.replace(".", "_")

dependencies {
    api(project(":korigadier"))

    minecraft("com.mojang:minecraft:$mc")
    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc:fabric-loader:0.18.2")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.13.6+kotlin.2.2.20")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.139.5+1.21.11")

    testImplementation(kotlin("test"))
}

kotlin { jvmToolchain(21) }

mavenPublishing {
    coordinates(
        groupId = project.group.toString(),
        artifactId = "korigadier-fabric-$mcUnderscore",
        version = project.version.toString()
    )

    publishToMavenCentral()
    signAllPublications()

    pom {
        name.set("Korigadier")
        description.set("Kotlin wrapper / DSL for Mojang Brigadier")
        url.set("https://github.com/oni0nfr1/Korigadier")

        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/licenses/MIT")
            }
        }
        developers {
            developer {
                id.set("oni0nfr1")
                name.set("Kim Tae Eon")
                email.set("taeun06@gmail.com")
            }
        }
        scm {
            url.set("https://github.com/oni0nfr1/Korigadier")
            connection.set("scm:git:git://github.com/oni0nfr1/Korigadier.git")
            developerConnection.set("scm:git:ssh://git@github.com:oni0nfr1/Korigadier.git")
        }
    }
}