package io.github.oni0nfr1.paperSample

import com.mojang.brigadier.context.CommandContext
import io.github.oni0nfr1.korigadier.api.Fragment
import io.github.oni0nfr1.korigadier.api.Args
import io.github.oni0nfr1.korigadier.api.fragment
import io.github.oni0nfr1.korigadier.api.get
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.command.brigadier.argument.ArgumentTypes as PaperArgs
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver
import org.bukkit.plugin.java.JavaPlugin

class Commands(plugin: JavaPlugin) {

    val createAndInvite: Fragment<CommandSourceStack> = fragment {
        literal("create") {
            argument("name", Args.word()) {
                requires { it.sender.isOp }
                executes(this@Commands::teamCreate)
            }
        }

        literal("invite") {
            argument("player", PaperArgs.player()) {
                executes(this@Commands::teamInvite)
            }
        }
    }

    fun teamCreate(ctx: CommandContext<CommandSourceStack>): Int {
        val name = ctx.get<String>("name")
        ctx.source.sender.sendMessage("팀 ${name}이 생성되었습니다! (테스트용 메세지)")
        // 팀 생성 로직...
        return 1
    }

    fun teamInvite(ctx: CommandContext<CommandSourceStack>): Int {
        val targets = ctx.get<PlayerSelectorArgumentResolver>("player")
            .resolve(ctx.source)
        targets.forEach {
                player ->
            ctx.source.sender.sendMessage("플레이어 ${player.name}이 초대되었습니다! (테스트용 메세지)")
        }
        // 초대 로직...
        return 1
    }
}