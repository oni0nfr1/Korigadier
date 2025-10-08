package io.github.oni0nfr1.korigadier.internal

import com.mojang.brigadier.arguments.ArgumentType
import io.github.oni0nfr1.korigadier.api.Fragment
import io.github.oni0nfr1.korigadier.api.KArgumentBuilder
import io.github.oni0nfr1.korigadier.api.KExec
import io.github.oni0nfr1.korigadier.api.KLiteralBuilder
import io.github.oni0nfr1.korigadier.internal.spec.KLiteralSpec

internal class KLiteralBuilderImpl<S>(
    private val name: String
) : KLiteralBuilder<S> {

    val spec = KLiteralSpec<S>(name)

    override fun requires(predicate: (S) -> Boolean) = apply {
        spec.predicates += predicate
    }

    override fun executes(exec: KExec<S>) = apply { spec.exec = exec }

    override fun <T> argument(
        name: String,
        type: ArgumentType<T>,
        block: KArgumentBuilder<S, T>.() -> Unit
    ) = apply {
        val child = KArgumentBuilderImpl<S, T>(name, type).apply(block).spec
        spec.children += child
    }

    override fun literal(name: String, block: KLiteralBuilder<S>.() -> Unit) = apply {
        val child = KLiteralBuilderImpl<S>(name).apply(block).spec
        spec.children += child
    }

    override fun include(fragment: Fragment<S>): KLiteralBuilder<S> {
        fragment.attachTo(this)
        return this
    }

    override fun meta(description: String?, examples: List<String>) = apply {
        spec.description = description
        spec.examples = examples
    }
}