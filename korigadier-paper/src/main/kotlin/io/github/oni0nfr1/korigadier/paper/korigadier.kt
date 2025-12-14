@file:Suppress("UNUSED")
package io.github.oni0nfr1.korigadier.paper

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.tree.CommandNode
import com.mojang.brigadier.tree.LiteralCommandNode
import io.github.oni0nfr1.korigadier.api.KRootScope
import io.papermc.paper.command.brigadier.Commands
import io.papermc.paper.plugin.lifecycle.event.registrar.ReloadableRegistrarEvent
import io.papermc.paper.command.brigadier.CommandSourceStack

fun <S> korigadier(
    event: ReloadableRegistrarEvent<Commands>,
    block: KRootScope<CommandSourceStack>.() -> Unit
) {
    val temp = CommandDispatcher<CommandSourceStack>()
    io.github.oni0nfr1.korigadier.api.korigadier(temp, block)

    val registrar: Commands = event.registrar()
    for (child: CommandNode<CommandSourceStack> in temp.root.children) {
        registrar.register(child as LiteralCommandNode)
    }
}