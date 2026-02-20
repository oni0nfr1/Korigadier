package io.github.oni0nfr1.paperSample

import com.mojang.brigadier.Command
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
            argument("name" to Args.word()) {
                requires { it.sender.isOp }
                executes(this@Commands::teamCreate)
            }
        }

        literal("invite") {
            argument("player" to PaperArgs.player()) {
                executes(this@Commands::teamInvite)
            }
        }

        literal("list_player_names") {
            argument(
                "player0" to PaperArgs.player(),
                "player1" to PaperArgs.player(),
                "player2" to PaperArgs.player(),
                "player3" to PaperArgs.player(),
                "player4" to PaperArgs.player(),
                "player5" to PaperArgs.player(),
                "player6" to PaperArgs.player(),
                "player7" to PaperArgs.player(),
                "player8" to PaperArgs.player(),
                "player9" to PaperArgs.player(),
            ) {
                executes(this@Commands::listNames)
            }
        }

        literal("foobar") {
            argument("foobar" to Args.word()) {
                suggests {
                    suggest("foo")
                    suggest("bar")
                }

                suggests(
                    "oof",
                    "rab",
                )

                executes(this@Commands::foobar)
            }
        }
    }

    fun teamCreate(ctx: CommandContext<CommandSourceStack>): Int {
        val name = ctx.get<String>("name")
        ctx.source.sender.sendMessage("팀 ${name}이 생성되었습니다! (테스트용 메세지)")
        // 팀 생성 로직...
        return Command.SINGLE_SUCCESS
    }

    fun teamInvite(ctx: CommandContext<CommandSourceStack>): Int {
        val targets = ctx.get<PlayerSelectorArgumentResolver>("player")
            .resolve(ctx.source)
        targets.forEach { player ->
            ctx.source.sender.sendMessage("플레이어 ${player.name}이 초대되었습니다! (테스트용 메세지)")
        }
        // 초대 로직...
        return Command.SINGLE_SUCCESS
    }

    fun listNames(ctx: CommandContext<CommandSourceStack>): Int {
        for (i in 0 until 10) {
            val target = ctx.get<PlayerSelectorArgumentResolver>("player$i")
                .resolve(ctx.source)
            val playerName = target[0].name
            ctx.source.sender.sendMessage("player$i: $playerName")
        }
        return Command.SINGLE_SUCCESS
    }

    fun foobar(ctx: CommandContext<CommandSourceStack>): Int {
        val word = ctx.get<String>("foobar")
        when (word) {
            "foo" -> ctx.source.sender.sendMessage("foo")
            "bar" -> ctx.source.sender.sendMessage("bar")
            else  -> ctx.source.sender.sendMessage("pardon?")
        }
        return Command.SINGLE_SUCCESS
    }
}