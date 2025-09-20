# Korigadier

**Brigadier API wrapper for Kotlin developers (in development)**

Mojang provides an open-source API called Brigadier for command engine.
However, this API is primarily designed for Java, and its style doesn’t fit naturally with Kotlin’s syntax. This often results in verbose and less readable code when writing commands in Kotlin.

Korigadier wraps Brigadier with a clean, Kotlin-style DSL, allowing you to define commands more concisely and with improved readability.
It is designed to avoid any Minecraft-version-specific dependencies, making it compatible with virtually any version that supports Brigadier.

(it is originally designed for Minecraft Paper Plugin Development)

---

# Korigadier

**코틀린을 위한 Brigadier API의 래퍼 라이브러리 (개발 중)**

모장은 오픈 소스 커맨드 엔진인 Brigadier를 제공합니다.
그러나 이 API는 Java를 기반으로 설계되어, Kotlin에서는 Kotlin만의 문법상 장점을 충분히 누리지 못합니다.

Korigadier는 Brigadier를 Kotlin 스타일 DSL로 래핑하여, 명령어를 훨씬 간결하고 읽기 쉽게 작성할 수 있도록 합니다.
또한 마인크래프트 버전에 따라 변하는 요소를 포함하지 않으므로, Brigadier를 지원하는 거의 모든 버전에서 문제없이 사용할 수 있습니다.

(기본적으로 Paper 플러그인 개발을 위해서 만든 라이브러리입니다. 다른 데서도 잘 쓸 수 있을지는 잘 모르겠네요)
