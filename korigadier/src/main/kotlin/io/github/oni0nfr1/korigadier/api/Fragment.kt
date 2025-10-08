// api/Fragment.kt
package io.github.oni0nfr1.korigadier.api

import io.github.oni0nfr1.korigadier.internal.fragmentImpl

interface Fragment<S> {
    /** target 리터럴에 이 프래그먼트를 복제하여 붙인다 */
    fun attachTo(target: KLiteralBuilder<S>)
}

/** 재사용 가능한 트리 조각을 정의 */
fun <S> fragment(block: KLiteralBuilder<S>.() -> Unit): Fragment<S> =
    fragmentImpl(block)
