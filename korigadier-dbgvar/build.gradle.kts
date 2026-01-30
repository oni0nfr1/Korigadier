plugins {
    alias(libs.plugins.kotlin.jvm)
    id("com.vanniktech.maven.publish") version "0.35.0"
    `java-library`
}

dependencies {
    implementation(project(":korigadier"))
    testImplementation(kotlin("test"))
    testImplementation(libs.junit.api)
    testRuntimeOnly(libs.junit.engine)
}

kotlin {
    jvmToolchain(21)
}

tasks.test { useJUnitPlatform() }

mavenPublishing {
    coordinates(
        groupId = project.group.toString(),
        artifactId = "korigadier-ext-dbgvar",
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