plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    id("fabric-loom") version "1.11-SNAPSHOT" apply false
    id("com.vanniktech.maven.publish") version "0.35.0"
}

allprojects {
    group = "io.github.oni0nfr1"
    version = "1.0.0"
    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}