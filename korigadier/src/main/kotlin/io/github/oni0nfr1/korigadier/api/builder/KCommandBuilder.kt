package io.github.oni0nfr1.korigadier.api.builder

import com.mojang.brigadier.arguments.ArgumentType
import io.github.oni0nfr1.korigadier.api.Fragment
import io.github.oni0nfr1.korigadier.api.KorigadierDsl

@KorigadierDsl
interface KCommandBuilder<S> {
    fun literal(name: String, block: KLiteralBuilder<S>.() -> Unit)
    fun <T> argument(name: String, type: ArgumentType<T>, block: KArgumentBuilder<S, T>.() -> Unit = {})

    fun include(fragment: Fragment<S>)
}