plugins {
    id("fabric-loom") version "1.11-SNAPSHOT"
    alias(libs.plugins.kotlin.jvm)
    `java-library`
    `maven-publish`
}

dependencies {
    api(project(":korigadier"))

    minecraft("com.mojang:minecraft:1.21.10")
    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc:fabric-loader:0.17.3")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.13.6+kotlin.2.2.20")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.135.0+1.21.10")

    testImplementation(kotlin("test"))
}

kotlin { jvmToolchain(21) }
java { withSourcesJar(); withJavadocJar() }

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            pom {
                name.set(project.name)
                description.set("Korigadier module: ${project.name}")
                url.set("https://github.com/taeun06/Korigadier")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://mit-license.org/")
                    }
                }
                developers {
                    developer {
                        id.set("oni0nfr1")
                        name.set("Kim Tae Eon") // 실명 싫으면 핸들/브랜드
                    }
                }
                scm {
                    url.set("https://github.com/oni0nfr1/korigadier")
                    connection.set("scm:git:https://github.com/oni0nfr1/korigadier.git")
                    developerConnection.set("scm:git:ssh://git@github.com/oni0nfr1/korigadier.git")
                }
            }
        }
    }
}