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