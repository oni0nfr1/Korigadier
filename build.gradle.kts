plugins { `maven-publish`; signing }

allprojects {
    group = "io.github.oni0nfr1.korigadier"
    version = "0.1.0"
    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}