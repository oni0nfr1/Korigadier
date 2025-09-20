package io.github.oni0nfr1.korigadier.internal

import com.mojang.brigadier.arguments.ArgumentType
import io.github.oni0nfr1.korigadier.api.KArgumentBuilder
import io.github.oni0nfr1.korigadier.api.KExec
import io.github.oni0nfr1.korigadier.api.KLiteralBuilder
import io.github.oni0nfr1.korigadier.internal.spec.KArgumentSpec

internal class KArgumentBuilderImpl<S, T>(
    private val name: String,
    private val type: ArgumentType<T>
) : KArgumentBuilder<S, T> {

    val spec = KArgumentSpec<S, T>(name, type)

    override fun suggests(provider: com.mojang.brigadier.suggestion.SuggestionsBuilder.(S) -> Unit) = apply {
        spec.suggests = { b, s -> provider(b, s) }
    }

    override fun executes(exec: KExec<S>) = apply { spec.exec = exec }

    override fun then(block: KLiteralBuilder<S>.() -> Unit) = apply {
        val child = KLiteralBuilderImpl<S>("<then>").apply(block).spec
        spec.children += child
    }
}