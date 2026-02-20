package io.github.oni0nfr1.korigadier.internal.spec

import com.mojang.brigadier.arguments.ArgumentType
import io.github.oni0nfr1.korigadier.api.builder.KExec
import io.github.oni0nfr1.korigadier.api.builder.SuggestionsBuilderFunc

internal data class KArgumentSpec<S, T>(
    override val name: String,
    val type: ArgumentType<T>,
    override var exec: KExec<S>? = null,
    override val predicates: MutableList<(S) -> Boolean> = mutableListOf(),
    override val children: MutableList<KNodeSpec<S>> = mutableListOf(),
    var suggests: MutableList<SuggestionsBuilderFunc<S>> = mutableListOf(),
) : KNodeSpec<S> {
    @Suppress("UNCHECKED_CAST")
    fun deepCopyAny(): KArgumentSpec<S, Any?> =
        KArgumentSpec(
            name = name,
            type = type, // ArgumentType<T>는 보통 상태가 없어서 공유 OK
            exec = exec,
            predicates = predicates,
            children = children.map { it.deepCopy() }.toMutableList(),
            suggests = suggests,
        ) as KArgumentSpec<S, Any?>
}