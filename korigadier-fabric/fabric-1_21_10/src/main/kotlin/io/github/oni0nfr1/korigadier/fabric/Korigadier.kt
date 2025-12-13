package io.github.oni0nfr1.korigadier.fabric

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.tree.CommandNode
import io.github.oni0nfr1.korigadier.api.KRootScope
import io.github.oni0nfr1.korigadier.api.korigadier

object Korigadier {
    fun <S> register(
        dispatcher: CommandDispatcher<S>,
        block: KRootScope<S>.() -> Unit
    ) {
        val temp = CommandDispatcher<S>()
        korigadier(temp, block)
        for (child: CommandNode<S> in temp.root.children) {
            dispatcher.root.addChild(child)
        }
    }
}