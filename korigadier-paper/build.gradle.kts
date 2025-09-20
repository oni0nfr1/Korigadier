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