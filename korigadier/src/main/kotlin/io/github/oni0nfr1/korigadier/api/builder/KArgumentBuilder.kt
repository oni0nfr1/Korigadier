package io.github.oni0nfr1.korigadier.api.builder

import com.mojang.brigadier.suggestion.SuggestionsBuilder
import io.github.oni0nfr1.korigadier.api.KorigadierDsl

typealias SuggestionsBuilderFunc<S> = SuggestionsBuilder.(S) -> Unit

@KorigadierDsl
interface KArgumentBuilder<S, T> : KCommandBuilder<S> {
    fun requires(predicate: (S) -> Boolean)
    fun suggests(vararg suggestions: String)
    fun suggests(provider: SuggestionsBuilderFunc<S>)
    fun executes(exec: KExec<S>)
}