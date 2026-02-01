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

    override fun <T> argument(name: String, type: ArgumentType<T>, block: KArgumentBuilder<S, T>.() -> Unit) {
        val child = KArgumentBuilderImpl<S, T>(name, type).apply(block).spec
        spec.children += child
    }

    override fun include(fragment: Fragment<S>) {
        fragment.attachTo(this)
    }
}