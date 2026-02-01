package io.github.oni0nfr1.korigadier.internal.spec

import io.github.oni0nfr1.korigadier.api.builder.KExec

internal data class KRootScopeSpec<S>(
    override val name: String = "<korigadier-root>",
    override val exec: KExec<S>? = null,
    override val predicates: MutableList<(S) -> Boolean> = mutableListOf(),
    override val children: MutableList<KNodeSpec<S>> = mutableListOf(),
) : KNodeSpec<S> {
    override fun deepCopy(): KNodeSpec<S> = KRootScopeSpec(
        name = name,
        exec = exec,
        predicates = predicates.toMutableList(),
        children = children.map { it.deepCopy() }.toMutableList()
    )
}