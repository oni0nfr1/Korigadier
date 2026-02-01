package io.github.oni0nfr1.korigadier.internal

import io.github.oni0nfr1.korigadier.api.Fragment
import io.github.oni0nfr1.korigadier.api.builder.KCommandBuilder
import io.github.oni0nfr1.korigadier.api.builder.KLiteralBuilder
import io.github.oni0nfr1.korigadier.internal.builder.KCommandBuilderImpl
import io.github.oni0nfr1.korigadier.internal.builder.KLiteralBuilderImpl
import io.github.oni0nfr1.korigadier.internal.spec.KLiteralSpec

internal class FragmentImpl<S>(
    internal val roots: List<KLiteralSpec<S>>
) : Fragment<S> {

    companion object {
        fun <S> create(block: KLiteralBuilder<S>.() -> Unit): FragmentImpl<S> {
            val temp = KLiteralBuilderImpl<S>("<fragment-root>")
            temp.apply(block)
            // 최상위 children 들만 조각으로 보관
            val roots = temp.spec.children.filterIsInstance<KLiteralSpec<S>>()
            return FragmentImpl(roots)
        }
    }

    override fun attachTo(target: KCommandBuilder<S>) {
        val t = target as KCommandBuilderImpl<S>
        roots.forEach { t.spec.children += it.deepCopy() }
    }
}