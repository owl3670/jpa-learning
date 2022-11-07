package com.spring.advanced.trace.template

import com.spring.advanced.trace.TraceStatus
import com.spring.advanced.trace.logtrace.LogTrace

abstract class AbstractTemplate<T>(
    private val trace: LogTrace
) {
    fun execute(message: String): T {
        var status:TraceStatus? = null
        try {
            status = trace.begin(message)
            val result: T = call()
            trace.end(status)
            return result
        }catch (e: Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }

    protected abstract fun call(): T
}