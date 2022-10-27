package com.spring.advanced.trace.hellotrace

import org.junit.jupiter.api.Test

class HelloTraceV1Test {
    @Test
    fun beginEnd() {
        val trace = HelloTraceV1()
        val status = trace.begin("Hello")
        trace.end(status)
    }

    @Test
    fun beginException(){
        val trace = HelloTraceV1()
        val status = trace.begin("Hello")
        trace.exception(status, IllegalStateException())
    }
}