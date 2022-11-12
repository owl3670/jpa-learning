package com.spring.advanced.trace.callback

import com.spring.advanced.trace.TraceStatus
import com.spring.advanced.trace.logtrace.LogTrace

class TraceTemplate(
    private val trace: LogTrace
) {
    fun <T> execute(message: String, callback: TraceCallback<T>): T{
        var status: TraceStatus? = null
        try {
            status = trace.begin(message)
            val result: T = callback.call()
            trace.end(status)
            return result
        } catch (e: Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }
}