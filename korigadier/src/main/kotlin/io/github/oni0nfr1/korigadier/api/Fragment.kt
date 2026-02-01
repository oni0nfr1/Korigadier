package io.github.oni0nfr1.korigadier.api

import io.github.oni0nfr1.korigadier.api.builder.KCommandBuilder
import io.github.oni0nfr1.korigadier.api.builder.KLiteralBuilder
import io.github.oni0nfr1.korigadier.internal.FragmentImpl

interface Fragment<S> {
    fun attachTo(target: KCommandBuilder<S>)
}

fun <S> fragment(block: KLiteralBuilder<S>.() -> Unit): Fragment<S> =
    FragmentImpl.create(block)
