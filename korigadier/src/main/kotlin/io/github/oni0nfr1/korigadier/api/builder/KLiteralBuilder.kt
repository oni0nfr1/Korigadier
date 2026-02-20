package io.github.oni0nfr1.korigadier.api.builder

import io.github.oni0nfr1.korigadier.api.KorigadierDsl

@KorigadierDsl
interface KLiteralBuilder<S> : KCommandBuilder<S> {
    fun requires(predicate: (S) -> Boolean)
    fun executes(exec: KExec<S>)

    /**
     * 구현되지 않았으며, **아무 동작도 하지 않습니다.**
     *
     * **추후 구현보다는 제거될 가능성이 높습니다.**
     */
    @Deprecated("")
    fun meta(description: String? = null, examples: List<String> = emptyList())
}