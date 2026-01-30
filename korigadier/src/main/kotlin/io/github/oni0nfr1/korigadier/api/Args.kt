package io.github.oni0nfr1.korigadier.api

import com.mojang.brigadier.arguments.*

/** 표준 Brigadier 인자 타입 팩토리 모음 */
object Args {
    fun word(): StringArgumentType = StringArgumentType.word()
    fun greedy(): StringArgumentType = StringArgumentType.greedyString()
    fun string(): StringArgumentType = StringArgumentType.string()
    fun bool(): BoolArgumentType = BoolArgumentType.bool()

    fun int(min: Int? = null, max: Int? = null): IntegerArgumentType = when {
        min != null && max != null -> IntegerArgumentType.integer(min, max)
        min != null -> IntegerArgumentType.integer(min)
        else -> IntegerArgumentType.integer()
    }

    fun long(min: Long? = null, max: Long? = null): LongArgumentType = when {
        min != null && max != null -> LongArgumentType.longArg(min, max)
        min != null -> LongArgumentType.longArg(min)
        else -> LongArgumentType.longArg()
    }

    fun float(min: Float? = null, max: Float? = null): FloatArgumentType = when {
        min != null && max != null -> FloatArgumentType.floatArg(min, max)
        min != null -> FloatArgumentType.floatArg(min)
        else -> FloatArgumentType.floatArg()
    }

    fun double(min: Double? = null, max: Double? = null): DoubleArgumentType = when {
        min != null && max != null -> DoubleArgumentType.doubleArg(min, max)
        min != null -> DoubleArgumentType.doubleArg(min)
        else -> DoubleArgumentType.doubleArg()
    }

}