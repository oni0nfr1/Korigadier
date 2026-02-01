package io.github.oni0nfr1.korigadier.internal.builder

import com.mojang.brigadier.CommandDispatcher
import io.github.oni0nfr1.korigadier.api.builder.KRootScope
import io.github.oni0nfr1.korigadier.internal.spec.KNodeSpec
import io.github.oni0nfr1.korigadier.internal.spec.KRootScopeSpec

internal class KRootScopeImpl<S>(
    override val dispatcher: CommandDispatcher<S>,
) : KCommandBuilderImpl<S>(), KRootScope<S> {
    override val spec = KRootScopeSpec<S>()

    fun builtRoots(): List<KNodeSpec<S>> = spec.children
}