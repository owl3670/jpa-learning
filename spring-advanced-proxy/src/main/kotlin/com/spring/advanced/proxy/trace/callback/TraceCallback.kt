package com.spring.advanced.proxy.trace.callback

fun interface TraceCallback<T> {
    fun call(): T
}