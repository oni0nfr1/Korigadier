package io.github.oni0nfr1.korigadier.internal.validate

import io.github.oni0nfr1.korigadier.internal.spec.*

internal object KValidator {

    fun <S> validate(roots: List<KLiteralSpec<S>>) {
        roots.forEach { validateLiteral(it, listOf(it.name)) }
    }

    private fun <S> validateLiteral(node: KLiteralSpec<S>, path: List<String>) {
        // 중복 리터럴 이름 검사
        val names = node.children.filterIsInstance<KLiteralSpec<S>>().map { it.name }
        if (names.size != names.toSet().size) {
            error("Duplicate literal under ${path.joinToString(" ")}")
        }

        node.children.forEach {
            when (it) {
                is KLiteralSpec<S> -> validateLiteral(it, path + it.name)
                is KArgumentSpec<S, *> -> validateArgument(it, path + "<${it.name}>")
            }
        }
    }

    private fun <S> validateArgument(node: KArgumentSpec<S, *>, path: List<String>) {
        // greedy string 뒤에 자식이 있으면 금지
        val isGreedy = node.type.javaClass.simpleName.contains("GreedyStringArgumentType")
        if (isGreedy && node.children.isNotEmpty()) {
            error("Greedy string must be last at ${path.joinToString(" ")}")
        }

        node.children.forEach {
            when (it) {
                is KLiteralSpec<S> -> validateLiteral(it, path + it.name)
                is KArgumentSpec<S, *> -> validateArgument(it, path + "<${it.name}>")
            }
        }
    }
}