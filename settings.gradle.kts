rootProject.name = "Korigadier"

pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/") {
            name = "Fabric"
        }
        gradlePluginPortal()
    }
}

fun includeSubModules(baseDirName: String) {
    val baseDir = file(baseDirName)
    if (!baseDir.exists() || !baseDir.isDirectory) return

    baseDir.listFiles { f -> f.isDirectory && !f.name.startsWith(".") }?.forEach { dir ->
        val hasBuildScript =
            dir.resolve("build.gradle.kts").exists() || dir.resolve("build.gradle").exists()

        if (hasBuildScript) {
            val path = ":$baseDirName:${dir.name}" // e.g. :novae-abilities:dash
            include(path)
            project(path).projectDir = dir
        }
    }
}

include("korigadier")
include("korigadier-paper")
include("korigadier-fabric")
include("examples:paper-sample")

includeSubModules("korigadier-fabric")
includeSubModules("korigadier-paper")