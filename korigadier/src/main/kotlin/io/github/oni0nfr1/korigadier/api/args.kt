package io.github.oni0nfr1.korigadier.api

import com.mojang.brigadier.arguments.*

/** 표준 Brigadier 인자 타입 팩토리 모음 */
object args {
    fun word(): StringArgumentType = StringArgumentType.word()
    fun greedy(): StringArgumentType = StringArgumentType.greedyString()
    fun string(): StringArgumentType = StringArgumentType.string()

    fun int(min: Int? = null, max: Int? = null): IntegerArgumentType = when {
        min != null && max != null -> IntegerArgumentType.integer(min, max)
        min != null -> IntegerArgumentType.integer(min)
        else -> IntegerArgumentType.integer()
    }

    fun bool(): BoolArgumentType = BoolArgumentType.bool()
}