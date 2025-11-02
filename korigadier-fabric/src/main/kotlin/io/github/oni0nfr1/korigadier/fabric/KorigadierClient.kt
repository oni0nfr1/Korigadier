package io.github.oni0nfr1.korigadier.fabric

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.tree.CommandNode
import io.github.oni0nfr1.korigadier.api.KRootScope
import io.github.oni0nfr1.korigadier.api.korigadier
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource

object KorigadierClient {
    fun register(block: KRootScope<FabricClientCommandSource>.() -> Unit) {
        ClientCommandRegistrationCallback.EVENT.register { dispatcher, _ ->
            val temp = CommandDispatcher<FabricClientCommandSource>()
            korigadier(temp, block)
            for (child: CommandNode<FabricClientCommandSource> in temp.root.children) {
                dispatcher.root.addChild(child)
            }
        }
    }
}