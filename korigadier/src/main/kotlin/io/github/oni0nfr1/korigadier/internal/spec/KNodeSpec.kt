package io.github.oni0nfr1.korigadier.internal.spec

internal interface KNodeSpec<S> {
    fun deepCopy(): KNodeSpec<S> = when (this) {
        is KLiteralSpec<S> -> this.deepCopy()
        is KArgumentSpec<S, *> -> this.deepCopyAny()
        else -> error("Unknown spec")
    }
}