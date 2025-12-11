# Korigadier

**코틀린을 위한 Brigadier API의 래퍼 라이브러리**

모장은 오픈 소스 커맨드 엔진인 Brigadier를 제공합니다.
그러나 이 API는 Java를 기반으로 설계되어, Kotlin에서는 Kotlin만의 문법상 장점을 충분히 누리지 못합니다.

Korigadier는 Brigadier를 Kotlin 스타일 DSL로 래핑하여, 명령어를 훨씬 간결하고 읽기 쉽게 작성할 수 있도록 합니다.

# 사용 방법
```kotlin
//build.gradle.kts
repositories {
    mavenCentral()
}

dependencies {
    //모든 아티팩트는 include()로 포함되거나 ShadowJar를 이용해야 함
    implementation("io.github.oni0nfr1:korigadier:1.0.0") // 모든 플랫폼 공통
    implementation("io.github.oni0nfr1:korigadier-paper-1_21_8:1.0.0") // Paper 플러그인을 개발하는 경우
    modImplementation("io.github.oni0nfr1:korigadier-fabric-1_21_10:1.0.0") // Fabric 모드를 개발하는 경우
}
```
```kotlin
// in Paper Plugin
lifecycleManager.registerEventHandler(LifecycleEvents.COMMANDS) { event ->
    Korigadier.register(event) {
        literal("team") {
            requires { it.sender.hasPermission("team.use") }
            literal("create") {
                argument("name", Args.word()) {
                    requires { it.sender.isOp }
                    executes { ctx ->
                        val name = ctx.get<String>("name")
                        ctx.source.sender.sendMessage("팀 ${name}이 생성되었습니다!")
                        // 팀 생성 로직...
                        1
                    }
                }
            }

            literal("invite") {
                argument("player", ArgumentTypes.player()) {
                    executes { ctx ->
                        val targets = ctx.get<PlayerSelectorArgumentResolver>("player")
                            .resolve(ctx.source)
                        targets.forEach {
                                player ->
                            ctx.source.sender.sendMessage("플레이어 ${player.name}이 초대되었습니다!")
                        }
                        // 초대 로직...
                        1
                    }
                }
            }
        }
    }
}
```

# 주의사항
라이선스는 기본적으로 MIT 라이선스이나, korigadier-paper 모듈만 GPL-3 라이선스가 적용됩니다.
```kotlin
dependencies {
    implementation("io.github.oni0nfr1:korigadier:1.0.0") // MIT
    implementation("io.github.oni0nfr1:korigadier-fabric-1_21_10:1.0.0") // MIT
    implementation("io.github.oni0nfr1:korigadier-paper-1_21_8:1.0.0") // GPL-3
}
```