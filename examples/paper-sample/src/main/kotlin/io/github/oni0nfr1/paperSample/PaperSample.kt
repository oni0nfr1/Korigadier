// examples/paper-sample/src/main/kotlin/.../SamplePlugin.kt
package io.github.oni0nfr1.paperSample

import io.github.oni0nfr1.korigadier.api.args
import io.github.oni0nfr1.korigadier.api.get
import io.github.oni0nfr1.korigadier.paper.Korigadier
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import org.bukkit.plugin.java.JavaPlugin

class PaperSample : JavaPlugin() {
    override fun onEnable() {
        lifecycleManager.registerEventHandler(LifecycleEvents.COMMANDS) { event ->
            Korigadier.register(event) {
                literal("team") {
                    requires { it.sender.hasPermission("team.use") }

                    literal("create") {
                        argument("name", args.word()) {
                            executes { ctx ->
                                val name = ctx.get<String>("name")
                                // 팀 생성 로직...
                                1
                            }
                        }
                    }

                    literal("invite") {
                        argument("player", args.word()) {
                            executes { ctx ->
                                val target = ctx.get<String>("player")
                                // 초대 로직...
                                1
                            }
                        }
                    }
                }
            }
        }
    }
}
