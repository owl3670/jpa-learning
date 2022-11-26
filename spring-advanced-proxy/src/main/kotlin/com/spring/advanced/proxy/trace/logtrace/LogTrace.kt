package com.spring.advanced.proxy.trace.logtrace

import com.spring.advanced.proxy.trace.TraceStatus

interface LogTrace {
    fun begin(message: String): TraceStatus
    fun end(status: TraceStatus)
    fun exception(status: TraceStatus, e: Exception)
}