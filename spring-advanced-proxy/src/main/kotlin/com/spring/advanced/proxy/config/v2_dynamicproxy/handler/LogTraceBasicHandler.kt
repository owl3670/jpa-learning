package com.spring.advanced.proxy.config.v2_dynamicproxy.handler

import com.spring.advanced.proxy.trace.TraceStatus
import com.spring.advanced.proxy.trace.logtrace.LogTrace
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogTraceBasicHandler(
    private val target: Any,
    private val logTrace: LogTrace
) : InvocationHandler {
    private val logger = mu.KotlinLogging.logger {}

    override fun invoke(proxy: Any, method: Method, args: Array<Any>?): Any {
        var status: TraceStatus? = null
        try {
            val message = method.declaringClass.simpleName + "." + method.name + "()"
            status = logTrace.begin(message)
            val result = method.invoke(target, *args ?: arrayOf())
            logTrace.end(status!!)
            return result
        } catch (e: Exception) {
            logTrace.exception(status!!, e)
            throw e
        }
    }
}