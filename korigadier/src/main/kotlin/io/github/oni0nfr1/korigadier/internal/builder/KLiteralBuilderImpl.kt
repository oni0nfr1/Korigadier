package io.github.oni0nfr1.korigadier.internal.builder

import com.mojang.brigadier.arguments.ArgumentType
import io.github.oni0nfr1.korigadier.api.builder.KArgumentBuilder
import io.github.oni0nfr1.korigadier.api.builder.KExec
import io.github.oni0nfr1.korigadier.api.builder.KLiteralBuilder
import io.github.oni0nfr1.korigadier.internal.spec.KLiteralSpec

internal class KLiteralBuilderImpl<S>(
    private val name: String
) : KLiteralBuilder<S> {

    val spec = KLiteralSpec<S>(name)

    override fun requires(predicate: (S) -> Boolean) {
        spec.predicates += predicate
    }

    override fun executes(exec: KExec<S>) { spec.exec = exec }

    override fun <T> argument(name: String, type: ArgumentType<T>, block: KArgumentBuilder<S, T>.() -> Unit) {
        val child = KArgumentBuilderImpl<S, T>(name, type).apply(block).spec
        spec.children += child
    }

    override fun literal(name: String, block: KLiteralBuilder<S>.() -> Unit) {
        val child = KLiteralBuilderImpl<S>(name).apply(block).spec
        spec.children += child
    }

    override fun meta(description: String?, examples: List<String>) {
        spec.description = description
        spec.examples = examples
    }
}