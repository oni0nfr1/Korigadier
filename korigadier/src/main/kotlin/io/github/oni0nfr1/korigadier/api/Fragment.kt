package io.github.oni0nfr1.korigadier.api

import io.github.oni0nfr1.korigadier.api.builder.KArgumentBuilder
import io.github.oni0nfr1.korigadier.api.builder.KLiteralBuilder
import io.github.oni0nfr1.korigadier.api.builder.KRootScope
import io.github.oni0nfr1.korigadier.internal.FragmentImpl

interface Fragment<S> {
    fun attachTo(target: KRootScope<S>)
    fun attachTo(target: KLiteralBuilder<S>)
    fun attachTo(target: KArgumentBuilder<S, *>)
}

fun <S> fragment(block: KLiteralBuilder<S>.() -> Unit): Fragment<S> =
    FragmentImpl.create(block)
