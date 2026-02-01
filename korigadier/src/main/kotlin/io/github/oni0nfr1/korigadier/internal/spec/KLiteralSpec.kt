package io.github.oni0nfr1.korigadier.internal.spec

import io.github.oni0nfr1.korigadier.api.builder.KExec

internal data class KLiteralSpec<S>(
    override val name: String,
    override var exec: KExec<S>? = null,
    override val predicates: MutableList<(S) -> Boolean> = mutableListOf(),
    override val children: MutableList<KNodeSpec<S>> = mutableListOf(),
    var description: String? = null,
    var examples: List<String> = emptyList()
) : KNodeSpec<S> {
    override fun deepCopy(): KLiteralSpec<S> =
        KLiteralSpec(
            name = name,
            exec = exec,
            predicates = predicates.toMutableList(),
            children = children.map { it.deepCopy() }.toMutableList(),
            description = description,
            examples = examples.toList()
        )
}