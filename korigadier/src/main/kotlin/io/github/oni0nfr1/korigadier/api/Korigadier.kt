package io.github.oni0nfr1.korigadier.api

import com.mojang.brigadier.CommandDispatcher

/**
 * DSL 진입점.
 * 사용자 DSL을 내부 스펙으로 수집 → 검증 → Brigadier 노드로 컴파일/등록.
 */
fun <S> korigadier(
    dispatcher: CommandDispatcher<S>,
    block: KRootScope<S>.() -> Unit
) {
    io.github.oni0nfr1.korigadier.internal.Entry.register(dispatcher, block)
}