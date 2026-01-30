package io.github.oni0nfr1.korigadier.api.builder

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import io.github.oni0nfr1.korigadier.api.Fragment
import io.github.oni0nfr1.korigadier.api.KorigadierDsl

typealias KExec<S> = (CommandContext<S>) -> Int

@KorigadierDsl
interface KRootScope<S>: KCommandBuilder<S> {
    val dispatcher: CommandDispatcher<S>
    fun include(fragment: Fragment<S>) { fragment.attachTo(this) }
}