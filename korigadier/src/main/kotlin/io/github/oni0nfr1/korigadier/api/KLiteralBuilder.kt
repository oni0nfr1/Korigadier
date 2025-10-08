package io.github.oni0nfr1.korigadier.api

import com.mojang.brigadier.arguments.ArgumentType

interface KLiteralBuilder<S> {
    fun requires(predicate: (S) -> Boolean)
    fun executes(exec: KExec<S>)
    fun <T> argument( name: String, type: ArgumentType<T>, block: KArgumentBuilder<S, T>.() -> Unit = {})
    fun literal(name: String, block: KLiteralBuilder<S>.() -> Unit)
    fun include(fragment: Fragment<S>) { fragment.attachTo(this) }

    // 선택: 헬프/문서화를 위한 메타
    fun meta(description: String? = null, examples: List<String> = emptyList())
}