package io.github.oni0nfr1.korigadier.api

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.suggestion.SuggestionsBuilder


interface KArgumentBuilder<S, T> {
    fun requires(predicate: (S) -> Boolean)
    fun suggests(provider: SuggestionsBuilder.(S) -> Unit)
    fun executes(exec: KExec<S>)
    fun <U> argument(name: String, type: ArgumentType<U>, block: KArgumentBuilder<S, U>.() -> Unit)
    fun literal(name: String, block: KLiteralBuilder<S>.() -> Unit)
    fun include(fragment: Fragment<S>) { fragment.attachTo(this) }
}

