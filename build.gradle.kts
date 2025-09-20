plugins { `maven-publish`; signing }

allprojects {
    group = "dev.onionfri.korigadier"
    version = "0.1.0"
    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}