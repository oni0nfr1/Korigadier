package io.github.oni0nfr1.korigadier.internal.builder

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.ArgumentType
import io.github.oni0nfr1.korigadier.api.builder.KArgumentBuilder
import io.github.oni0nfr1.korigadier.api.builder.KLiteralBuilder
import io.github.oni0nfr1.korigadier.api.builder.KRootScope
import io.github.oni0nfr1.korigadier.internal.spec.KNodeSpec

internal class KRootScopeImpl<S>(
    override val dispatcher: CommandDispatcher<S>,
    internal val roots: MutableList<KNodeSpec<S>> = mutableListOf()
) : KRootScope<S> {
    override fun literal(name: String, block: KLiteralBuilder<S>.() -> Unit) {
        val node = KLiteralBuilderImpl<S>(name).apply(block).spec
        roots += node
    }

    override fun <T> argument(name: String, type: ArgumentType<T>, block: KArgumentBuilder<S, T>.() -> Unit) {
        val child = KArgumentBuilderImpl<S, T>(name, type).apply(block).spec
        roots += child
    }

    fun builtRoots(): List<KNodeSpec<S>> = roots
}