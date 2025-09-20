package io.github.oni0nfr1.korigadier.api

import com.mojang.brigadier.suggestion.SuggestionsBuilder

interface KArgumentBuilder<S, T> {
    fun suggests(provider: SuggestionsBuilder.(S) -> Unit): KArgumentBuilder<S, T>
    fun executes(exec: KExec<S>): KArgumentBuilder<S, T>
    fun then(block: KLiteralBuilder<S>.() -> Unit): KArgumentBuilder<S, T>
}
