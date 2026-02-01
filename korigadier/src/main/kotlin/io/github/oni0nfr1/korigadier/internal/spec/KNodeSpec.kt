package io.github.oni0nfr1.korigadier.internal.spec

import io.github.oni0nfr1.korigadier.api.builder.KExec

internal interface KNodeSpec<S> {

    val name: String
    val exec: KExec<S>?
    val predicates: MutableList<(S) -> Boolean>
    val children: MutableList<KNodeSpec<S>>

    fun deepCopy(): KNodeSpec<S> = when (this) {
        is KRootScopeSpec<S> -> this.deepCopy()
        is KLiteralSpec<S> -> this.deepCopy()
        is KArgumentSpec<S, *> -> this.deepCopyAny()
        else -> error("Unknown spec")
    }
}