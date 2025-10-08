package io.github.oni0nfr1.korigadier.internal.spec

import io.github.oni0nfr1.korigadier.api.KExec

internal data class KLiteralSpec<S>(
    val name: String,
    val predicates: MutableList<(S) -> Boolean> = mutableListOf(),
    var exec: KExec<S>? = null,
    val children: MutableList<KNodeSpec<S>> = mutableListOf(),
    var description: String? = null,
    var examples: List<String> = emptyList()
) : KNodeSpec<S> {
    override fun deepCopy(): KLiteralSpec<S> =
    KLiteralSpec(
        name = name,
        predicates = predicates.toMutableList(),
        exec = exec,
        children = children.map { it.deepCopy() }.toMutableList(),
        description = description,
        examples = examples.toList()
    )
}