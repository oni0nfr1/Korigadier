package io.github.oni0nfr1.korigadier.api.builder

import com.mojang.brigadier.arguments.ArgumentType
import io.github.oni0nfr1.korigadier.api.Fragment
import io.github.oni0nfr1.korigadier.api.KorigadierDsl

typealias LiteralBuilderFunc<S> = KLiteralBuilder<S>.() -> Unit
typealias ArgumentBuilderFunc<S, T> = KArgumentBuilder<S, T>.() -> Unit

@KorigadierDsl
interface KCommandBuilder<S> {
    fun literal(name: String, block: LiteralBuilderFunc<S>)

    @Deprecated("deprecated since 1.1.1", ReplaceWith("argument(name to type, block)"), DeprecationLevel.WARNING)
    fun <T> argument(name: String, type: ArgumentType<T>, block: ArgumentBuilderFunc<S, T> = {})

    fun <T> argument(
        nameAndType: Pair<String, ArgumentType<T>>,
        vararg moreNameAndTypes: Pair<String, ArgumentType<T>>,
        block: KArgumentBuilder<S, T>.() -> Unit = {}
    )

    fun include(fragment: Fragment<S>)
}