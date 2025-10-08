package io.github.oni0nfr1.korigadier.internal

import io.github.oni0nfr1.korigadier.api.Fragment
import io.github.oni0nfr1.korigadier.api.KLiteralBuilder
import io.github.oni0nfr1.korigadier.internal.spec.KLiteralSpec

internal class FragmentImpl<S>(
    private val roots: List<KLiteralSpec<S>>
) : Fragment<S> {
    override fun attachTo(target: KLiteralBuilder<S>) {
        // target은 KLiteralBuilderImpl<S>일 것이므로 spec 접근 필요
        val t = target as KLiteralBuilderImpl<S>
        roots.forEach { t.spec.children += it.deepCopy() }
    }
}

// API 함수 구현부
fun <S> fragmentImpl(block: KLiteralBuilder<S>.() -> Unit): Fragment<S> {
    val temp = KLiteralBuilderImpl<S>("<fragment-root>")
    temp.apply(block)
    // 최상위 children 들만 조각으로 보관
    val roots = temp.spec.children.filterIsInstance<KLiteralSpec<S>>()
    return FragmentImpl(roots)
}