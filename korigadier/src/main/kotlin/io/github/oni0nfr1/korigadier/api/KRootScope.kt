package io.github.oni0nfr1.korigadier.api

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext

typealias KExec<S> = (CommandContext<S>) -> Int

interface KRootScope<S> {
    val dispatcher: CommandDispatcher<S>
    fun literal(name: String, block: KLiteralBuilder<S>.() -> Unit)
}