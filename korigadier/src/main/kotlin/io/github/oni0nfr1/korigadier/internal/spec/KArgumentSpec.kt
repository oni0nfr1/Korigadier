package io.github.oni0nfr1.korigadier.internal.spec

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import io.github.oni0nfr1.korigadier.api.KExec

internal data class KArgumentSpec<S, T>(
    val name: String,
    val type: ArgumentType<T>,
    var suggests: ((SuggestionsBuilder, S) -> Unit)? = null,
    var exec: KExec<S>? = null,
    val children: MutableList<KNodeSpec<S>> = mutableListOf()
) : KNodeSpec<S>