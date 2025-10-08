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
            }
        }
    }
}