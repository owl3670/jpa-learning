package com.spring.advanced.proxy.trace

class TraceStatus(
    val traceId: TraceId,
    val startTimeMs: Long,
    val message: String
)