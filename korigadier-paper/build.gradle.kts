plugins {
    alias(libs.plugins.kotlin.jvm)
    `java-library`
    `maven-publish`
}

dependencies {
    api(project(":korigadier"))   // 코어(= API+internal)에 의존
    compileOnly(libs.paper.api)   // 서버가 제공함 → compileOnly
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
                        name.set("GNU General Public License v3.0 or later")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.html")
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