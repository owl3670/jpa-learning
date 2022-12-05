package com.spring.advanced.proxy.config.v2_dynamicproxy.handler

import com.spring.advanced.proxy.trace.TraceStatus
import com.spring.advanced.proxy.trace.logtrace.LogTrace
import org.springframework.util.PatternMatchUtils
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogTraceFilterHandler(
    private val target: Any,
    private val logTrace: LogTrace,
    private val patterns: Array<String>
) : InvocationHandler {
    override fun invoke(proxy: Any, method: Method, args: Array<Any>?): Any {
        val methodName = method.name
        if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
            return method.invoke(target, *args.orEmpty())
        }

        var status: TraceStatus? = null
        try {
            val message = method.declaringClass.simpleName + "." + methodName + "()"
            status = logTrace.begin(message)
            val result = method.invoke(target, *args.orEmpty())
            logTrace.end(status)
            return result
        } catch (e: Exception) {
            logTrace.exception(status!!, e)
            throw e
        }
    }
}