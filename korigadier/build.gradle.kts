plugins {
    alias(libs.plugins.kotlin.jvm)
    `java-library`
    `maven-publish`
}

dependencies {
    api(libs.brigadier)           // 공개 표면은 Brigadier만 보이게
    testImplementation(kotlin("test"))
    testImplementation(libs.junit.api)
    testRuntimeOnly(libs.junit.engine)
}

kotlin {
    jvmToolchain(21)
}

tasks.test { useJUnitPlatform() }

java { withSourcesJar(); withJavadocJar() }

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"]) // 표준 java 컴포넌트 퍼블리시
            pom {
                name.set(project.name)
                description.set("Korigadier module: ${project.name}")
                url.set("https://github.com/taeun06/Korigadier") // 선택
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
            }
        }
    }
}