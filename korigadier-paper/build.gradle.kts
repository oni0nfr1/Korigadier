plugins {
    kotlin("jvm") version "2.2.0"
}

group = "io.github.oni0nfr1"
version = "unspecified"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}