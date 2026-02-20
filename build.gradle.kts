plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    id("com.vanniktech.maven.publish") version "0.35.0"
}

allprojects {
    group = "io.github.oni0nfr1"
    version = "1.1.1"
    repositories {
        mavenLocal() // for testing before launching to maven central
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}