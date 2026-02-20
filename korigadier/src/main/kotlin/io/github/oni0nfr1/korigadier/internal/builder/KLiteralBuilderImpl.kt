package io.github.oni0nfr1.korigadier.internal.builder

import io.github.oni0nfr1.korigadier.api.builder.KExec
import io.github.oni0nfr1.korigadier.api.builder.KLiteralBuilder
import io.github.oni0nfr1.korigadier.internal.spec.KLiteralSpec

internal class KLiteralBuilderImpl<S>(
    private val name: String
) : KCommandBuilderImpl<S>(), KLiteralBuilder<S> {

    override val spec = KLiteralSpec<S>(name)

    override fun requires(predicate: (S) -> Boolean) {
        spec.predicates += predicate
    }

    override fun executes(exec: KExec<S>) {
        spec.exec = exec
    }

    @Deprecated("")
    override fun meta(description: String?, examples: List<String>) {
        spec.description = description
        spec.examples = examples
    }
}