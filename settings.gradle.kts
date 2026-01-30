rootProject.name = "Korigadier"

pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/") {
            name = "Fabric"
        }
        gradlePluginPortal()
    }
}

include("korigadier")
include("korigadier-paper")
include("examples:paper-sample")
include("korigadier-dbgvar")