package io.github.oni0nfr1.korigadier.internal

import com.mojang.brigadier.CommandDispatcher
import io.github.oni0nfr1.korigadier.api.KRootScope
import io.github.oni0nfr1.korigadier.internal.compiler.KCompiler
import io.github.oni0nfr1.korigadier.internal.validate.KValidator

internal object Entry {
    fun <S> register(dispatcher: CommandDispatcher<S>, block: KRootScope<S>.() -> Unit) {
        // 1) DSL 수집
        val root = KRootScopeImpl(dispatcher)
        root.block()
        val specs = root.builtRoots()

        // 2) 검증
        KValidator.validate(specs)

        // 3) 컴파일 & 등록
        KCompiler.registerAll(dispatcher, specs)
    }
}