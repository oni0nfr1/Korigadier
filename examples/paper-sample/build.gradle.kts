plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.shadow)
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

dependencies {
    implementation("io.github.oni0nfr1:korigadier:1.0.0")
    implementation("io.github.oni0nfr1:korigadier-paper-1_21_8:1.0.0")
    compileOnly(libs.paper.api)
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
        minecraftVersion("1.21.8")
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
