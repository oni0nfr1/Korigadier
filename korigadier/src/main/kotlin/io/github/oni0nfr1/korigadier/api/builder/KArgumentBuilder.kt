package io.github.oni0nfr1.korigadier.api.builder

import com.mojang.brigadier.suggestion.SuggestionsBuilder
import io.github.oni0nfr1.korigadier.api.KorigadierDsl

@KorigadierDsl
interface KArgumentBuilder<S, T> : KCommandBuilder<S> {
    fun requires(predicate: (S) -> Boolean)
    fun suggests(provider: SuggestionsBuilder.(S) -> Unit)
    fun executes(exec: KExec<S>)
}