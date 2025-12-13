package io.github.oni0nfr1.korigadier.paper

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.tree.CommandNode
import com.mojang.brigadier.tree.LiteralCommandNode
import io.github.oni0nfr1.korigadier.api.KRootScope
import io.github.oni0nfr1.korigadier.api.korigadier
import io.papermc.paper.command.brigadier.Commands
import io.papermc.paper.plugin.lifecycle.event.registrar.ReloadableRegistrarEvent
import io.papermc.paper.command.brigadier.CommandSourceStack

object Korigadier {
    fun register(
        event: ReloadableRegistrarEvent<Commands>,
        block: KRootScope<CommandSourceStack>.() -> Unit
    ) {
        val temp = CommandDispatcher<CommandSourceStack>()
        korigadier(temp, block)

        val registrar = event.registrar() // 타입: io.papermc.paper.command.brigadier.Commands
        for (child: CommandNode<CommandSourceStack> in temp.root.children) {
            registrar.register(child as LiteralCommandNode) // 설명/별칭 추가 오버로드도 존재
        }
    }
}