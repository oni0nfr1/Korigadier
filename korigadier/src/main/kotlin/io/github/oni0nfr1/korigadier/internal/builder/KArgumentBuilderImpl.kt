package io.github.oni0nfr1.korigadier.internal.builder

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import io.github.oni0nfr1.korigadier.api.builder.KArgumentBuilder
import io.github.oni0nfr1.korigadier.api.builder.KExec
import io.github.oni0nfr1.korigadier.api.builder.KLiteralBuilder
import io.github.oni0nfr1.korigadier.internal.spec.KArgumentSpec

internal class KArgumentBuilderImpl<S, T>(
    private val name: String,
    private val type: ArgumentType<T>
) : KArgumentBuilder<S, T> {

    val spec = KArgumentSpec<S, T>(name, type)

    override fun requires(predicate: (S) -> Boolean) {
        spec.predicates += predicate
    }

    override fun suggests(provider: SuggestionsBuilder.(S) -> Unit)  {
        spec.suggests = { b, s -> provider(b, s) }
    }

    override fun executes(exec: KExec<S>)  { spec.exec = exec }

    override fun <T> argument(name: String, type: ArgumentType<T>, block: KArgumentBuilder<S, T>.() -> Unit) {
        val child = KArgumentBuilderImpl<S, T>(name, type).apply(block).spec
        spec.children += child
    }

    override fun literal(name: String, block: KLiteralBuilder<S>.() -> Unit) {
        val child = KLiteralBuilderImpl<S>(name).apply(block).spec
        spec.children += child
    }
}