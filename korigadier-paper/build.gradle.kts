plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.shadow)
    id("com.vanniktech.maven.publish") version "0.35.0"
}

var mc = "1.21.11"
var mcUnderscore = mc.replace(".", "_")

dependencies {
    implementation(project(":korigadier"))
    compileOnly($$"io.papermc.paper:paper-api:$$mc-R0.1-SNAPSHOT")
    testImplementation(kotlin("test"))
}

kotlin { jvmToolchain(21) }

tasks.build {
    dependsOn("shadowJar")
}

mavenPublishing {
    coordinates(
        groupId = project.group.toString(),
        artifactId = "korigadier-paper",
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
                name.set("GNU General Public License v3.0")
                url.set("https://www.gnu.org/licenses/gpl-3.0-standalone.html")
                distribution.set("repo")
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