package io.github.oni0nfr1.paperSample

import io.github.oni0nfr1.korigadier.paper.Korigadier
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import org.bukkit.plugin.java.JavaPlugin

class PaperSample : JavaPlugin() {

    lateinit var commands: Commands

    override fun onEnable() {
        commands = Commands(this)

        lifecycleManager.registerEventHandler(LifecycleEvents.COMMANDS) { event ->
            Korigadier.register(event) {
                literal("team") {
                    requires { it.sender.hasPermission("team.use") }
                    include(commands.createAndInvite)
                }
            }
        }
    }
}
