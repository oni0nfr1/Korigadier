package io.github.oni0nfr1.korigadier.api

import com.mojang.brigadier.context.CommandContext

inline fun <reified T> CommandContext<*>.get(name: String): T =
    this.getArgument(name, T::class.java)
