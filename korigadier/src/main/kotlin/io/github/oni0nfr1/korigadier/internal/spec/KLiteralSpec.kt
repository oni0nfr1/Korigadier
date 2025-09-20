package io.github.oni0nfr1.korigadier.internal.spec

import io.github.oni0nfr1.korigadier.api.KExec

internal data class KLiteralSpec<S>(
    val name: String,
    val predicates: MutableList<(S) -> Boolean> = mutableListOf(),
    var exec: KExec<S>? = null,
    val children: MutableList<KNodeSpec<S>> = mutableListOf(),
    var description: String? = null,
    var examples: List<String> = emptyList()
) : KNodeSpec<S>