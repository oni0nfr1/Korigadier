# Korigadier

**코틀린을 위한 Brigadier API의 래퍼 라이브러리**

모장은 오픈 소스 커맨드 엔진인 Brigadier를 제공합니다.
그러나 이 API는 Java를 기반으로 설계되어, Kotlin에서는 Kotlin만의 문법상 장점을 충분히 누리지 못합니다.

Korigadier는 Brigadier를 Kotlin 스타일 DSL로 래핑하여, 명령어를 훨씬 간결하고 읽기 쉽게 작성할 수 있도록 합니다.

## 호환성
brigadier를 사용하는 모든 시스템에서 호환됩니다.  
단, Paper의 경우 후술할 호환 브릿지가 포함된 버전이 권장됩니다.

# 사용 방법 (Usage)
**In build.gradle.kts**
```kotlin
repositories {
    mavenCentral()
}

dependencies {
    // use ShadowJar or else to ensure this library is included in your JAR
    // ShadowJar 등을 이용하여 빌드된 JAR 파일에 이 라이브러리가 포함되게 하십시오.
    implementation("io.github.oni0nfr1:korigadier-paper:1.0.2") // in Paper Plugin
    implementation("io.github.oni0nfr1:korigadier:1.0.2") // else
}
```
**In Fabric Mod**
```kotlin
ClientCommandRegistrationCallback.EVENT.register { dispatcher, _ ->
    korigadier(event) {
        literal("team") {
            requires { it.sender.hasPermission("team.use") }
            literal("create") {
                argument("name", Args.word()) {
                    requires { it.sender.isOp }
                    executes { ctx ->
                        val name = ctx.get<String>("name")
                        ctx.source.sender.sendMessage("팀 ${name}이 생성되었습니다!")
                        // team create logics...
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
                        // team invitation logics...
                        1
                    }
                }
            }
        }
    }
}
```
**In Paper Plugin**
```kotlin
lifecycleManager.registerEventHandler(LifecycleEvents.COMMANDS) { event ->
    korigadier<CommandSourceStack>(event) {
        literal("team") {
            requires { it.sender.hasPermission("team.use") }
            // ... same as above
        }
    }
}
```

# 주의사항
라이선스는 기본적으로 MIT 라이선스이나, korigadier-paper 모듈만 GPL-3 라이선스가 적용됩니다.
```kotlin
dependencies {
    implementation("io.github.oni0nfr1:korigadier:1.0.2") // MIT
    implementation("io.github.oni0nfr1:korigadier-paper:1.0.2") // GPL-3
}
```