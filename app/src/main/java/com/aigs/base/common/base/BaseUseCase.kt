package com.aigs.base.common.base

abstract class BaseUseCase<in Params, out T> {
    abstract fun execute(params: Params): T
}