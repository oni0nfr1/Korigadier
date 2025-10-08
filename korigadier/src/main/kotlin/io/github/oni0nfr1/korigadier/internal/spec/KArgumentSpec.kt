package io.github.oni0nfr1.korigadier.internal.spec

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import io.github.oni0nfr1.korigadier.api.KExec

internal data class KArgumentSpec<S, T>(
    val name: String,
    val type: ArgumentType<T>,
    var suggests: ((SuggestionsBuilder, S) -> Unit)? = null,
    var exec: KExec<S>? = null,
    val children: MutableList<KNodeSpec<S>> = mutableListOf()
) : KNodeSpec<S> {
    @Suppress("UNCHECKED_CAST")
    fun deepCopyAny(): KArgumentSpec<S, Any?> =
    KArgumentSpec(
        name = name,
        type = type, // ArgumentType<T>는 보통 상태가 없어서 공유 OK
        suggests = suggests,
        exec = exec,
        children = children.map { it.deepCopy() }.toMutableList()
    ) as KArgumentSpec<S, Any?>
}