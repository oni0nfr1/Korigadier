package io.github.oni0nfr1.korigadier.api.builder

import io.github.oni0nfr1.korigadier.api.Fragment
import io.github.oni0nfr1.korigadier.api.KorigadierDsl

@KorigadierDsl
interface KLiteralBuilder<S>: KCommandBuilder<S> {
    fun requires(predicate: (S) -> Boolean)
    fun executes(exec: KExec<S>)
    fun include(fragment: Fragment<S>) { fragment.attachTo(this) }

    // 선택: 헬프/문서화를 위한 메타
    fun meta(description: String? = null, examples: List<String> = emptyList())
}