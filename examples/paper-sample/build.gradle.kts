plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.shadow)
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

var mc = "1.21.11"

dependencies {
    implementation(project(":korigadier-paper"))
    compileOnly($$"io.papermc.paper:paper-api:$$mc-R0.1-SNAPSHOT")
}

tasks.processResources {
    filesMatching("paper-plugin.yml") {
        expand("version" to project.version)
    }
}

tasks {
    runServer {
        // Configure the Minecraft version for our task.
        // This is the only required configuration besides applying the plugin.
        // Your plugin's jar (or shadowJar if present) will be used automatically.
        minecraftVersion(mc)
        runDirectory.set(rootProject.layout.projectDirectory.dir("run"))
    }
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks.build {
    dependsOn("shadowJar")
}
