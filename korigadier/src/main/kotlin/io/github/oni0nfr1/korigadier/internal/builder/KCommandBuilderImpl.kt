package io.github.oni0nfr1.korigadier.internal.builder

import com.mojang.brigadier.arguments.ArgumentType
import io.github.oni0nfr1.korigadier.api.Fragment
import io.github.oni0nfr1.korigadier.api.builder.KArgumentBuilder
import io.github.oni0nfr1.korigadier.api.builder.KCommandBuilder
import io.github.oni0nfr1.korigadier.api.builder.KLiteralBuilder
import io.github.oni0nfr1.korigadier.internal.spec.KNodeSpec

internal abstract class KCommandBuilderImpl<S> : KCommandBuilder<S> {

    abstract val spec: KNodeSpec<S>

    override fun literal(name: String, block: KLiteralBuilder<S>.() -> Unit) {
        val child = KLiteralBuilderImpl<S>(name).apply(block).spec
        spec.children += child
    }

    @Deprecated("", replaceWith = ReplaceWith("argument(name to type)"))
    override fun <T> argument(name: String, type: ArgumentType<T>, block: KArgumentBuilder<S, T>.() -> Unit) {
        val child = KArgumentBuilderImpl<S, T>(name, type).apply(block).spec
        spec.children += child
    }

    override fun <T> argument(
        nameAndType: Pair<String, ArgumentType<T>>,
        vararg moreNameAndTypes: Pair<String, ArgumentType<T>>,
        block: KArgumentBuilder<S, T>.() -> Unit
    ) {
        var leaf: KArgumentBuilderImpl<S, T> = KArgumentBuilderImpl(nameAndType.first, nameAndType.second)
        spec.children += leaf.spec

        for (nameAndType in moreNameAndTypes) {
            nameAndType.run {
                val child = KArgumentBuilderImpl<S, T>(first, second)
                leaf.spec.children += child.spec
                leaf = child
            }
        }

        leaf.apply(block)
    }

    override fun include(fragment: Fragment<S>) {
        fragment.attachTo(this)
    }
}