package io.github.oni0nfr1.korigadier.internal

import io.github.oni0nfr1.korigadier.api.KLiteralBuilder
import io.github.oni0nfr1.korigadier.api.KRootScope
import io.github.oni0nfr1.korigadier.internal.spec.KLiteralSpec

internal class KRootScopeImpl<S>(
    override val dispatcher: com.mojang.brigadier.CommandDispatcher<S>,
    private val roots: MutableList<KLiteralSpec<S>> = mutableListOf()
) : KRootScope<S> {
    override fun literal(name: String, block: KLiteralBuilder<S>.() -> Unit) {
        val node = KLiteralBuilderImpl<S>(name).apply(block).spec
        roots += node
    }
    fun builtRoots(): List<KLiteralSpec<S>> = roots
}