package com.spring.advanced.proxy.config.v3_proxyfactory.advice

import com.spring.advanced.proxy.trace.TraceStatus
import com.spring.advanced.proxy.trace.logtrace.LogTrace
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation

class LogTraceAdvice(
    private val logTrace: LogTrace
) : MethodInterceptor{
    override fun invoke(invocation: MethodInvocation): Any? {
        var status: TraceStatus? = null

        try {
            val method = invocation.method
            val message = method.declaringClass.simpleName + "." + method.name + "()"
            status = logTrace.begin(message)
            val result = invocation.proceed()
            logTrace.end(status)
            return result
        } catch (e: Exception) {
            logTrace.exception(status!!, e)
            throw e
        }
    }
}