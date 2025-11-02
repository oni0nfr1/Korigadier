plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    `maven-publish`
    signing
    id("org.jreleaser") version "1.20.0"
    id("fabric-loom") version "1.11-SNAPSHOT" apply false
}

allprojects {
    group = "io.github.oni0nfr1.korigadier"
    version = "0.1.0"
    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

jreleaser {
    // VCS 루트를 자동 탐색(하위 폴더에서 실행해도 감지)
    gitRootSearch.set(true)

    signing {
        active.set(org.jreleaser.model.Active.NEVER) // JReleaser가 서명도 할 때
        armored.set(false)
    }

    release {
        github {
            repoOwner = "oni0nfr1"
            overwrite = true
        }
    }

    deploy {
        maven {
            mavenCentral { /* 토큰은 env로 주입 */ }
        }
    }
}