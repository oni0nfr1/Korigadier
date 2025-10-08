package io.github.oni0nfr1.korigadier.internal.compiler

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import com.mojang.brigadier.tree.CommandNode
import io.github.oni0nfr1.korigadier.internal.spec.*

internal object KCompiler {

    fun <S> registerAll(dispatcher: CommandDispatcher<S>, roots: List<KLiteralSpec<S>>) {
        roots.forEach { root ->
            dispatcher.root.addChild(compileLiteral(root))
        }
    }

    private fun <S> compileLiteral(spec: KLiteralSpec<S>): CommandNode<S> {
        val b = LiteralArgumentBuilder.literal<S>(spec.name) // 빌더 생성

        if (spec.predicates.isNotEmpty()) {
            b.requires { s -> spec.predicates.all { it(s) } }
        } // 권한 제한 적용

        spec.exec?.let { exec -> b.executes { ctx -> exec(ctx) } } // 실행 로직 적용

        spec.children.forEach { child -> b.then(compileNode(child)) } // 하위 노드도 연쇄 컴파일
        return b.build() //빌드하여 마무리
    }

    @Suppress("UNCHECKED_CAST")
    private fun <S> compileNode(spec: KNodeSpec<S>): CommandNode<S> = when (spec) {
        is KLiteralSpec<S> -> compileLiteral(spec)
        is KArgumentSpec<S, *> -> compileArgument(spec as KArgumentSpec<S, Any?>)
        else -> error("Unknown spec: $spec")
    }

    private fun <S> compileArgument(spec: KArgumentSpec<S, Any?>): CommandNode<S> {
        val b = RequiredArgumentBuilder.argument<S, Any?>(spec.name, spec.type)
        if (spec.predicates.isNotEmpty()) {
            b.requires { s -> spec.predicates.all { it(s) } }
        } // 권한 제한 적용

        spec.suggests?.let { provider ->
            b.suggests { ctx, builder ->
                @Suppress("UNCHECKED_CAST")
                provider(builder, ctx.source as S)
                java.util.concurrent.CompletableFuture.completedFuture(builder.build())
            }
        } // 추천 적용

        spec.exec?.let { exec -> b.executes { ctx -> exec(ctx) } } // 실행 로직 적용

        spec.children.forEach { child -> b.then(compileNode(child)) } // 하위 노드도 연쇄 컴파일
        return b.build()
    }
}