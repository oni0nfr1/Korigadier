package io.github.oni0nfr1.korigadier.internal.builder

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import io.github.oni0nfr1.korigadier.api.builder.KArgumentBuilder
import io.github.oni0nfr1.korigadier.api.builder.KExec
import io.github.oni0nfr1.korigadier.api.builder.SuggestionsBuilderFunc
import io.github.oni0nfr1.korigadier.internal.spec.KArgumentSpec

internal class KArgumentBuilderImpl<S, T>(
    private val name: String,
    private val type: ArgumentType<T>
) : KCommandBuilderImpl<S>(), KArgumentBuilder<S, T> {

    override val spec = KArgumentSpec<S, T>(name, type)

    override fun requires(predicate: (S) -> Boolean) {
        spec.predicates += predicate
    }

    override fun suggests(vararg suggestions: String) {
        spec.suggests += { _ -> suggestions.forEach { suggest(it) } }
    }

    override fun suggests(provider: SuggestionsBuilderFunc<S>) {
        spec.suggests += provider
    }

    override fun executes(exec: KExec<S>) {
        spec.exec = exec
    }
}