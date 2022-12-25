package com.spring.advanced.proxy.config.v6_aop.aspect

import com.spring.advanced.proxy.trace.TraceStatus
import com.spring.advanced.proxy.trace.logtrace.LogTrace
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

@Aspect
class LogTraceAspect(
    private val logTrace: LogTrace
) {
    @Around("execution(* com.spring.advanced.proxy.app..*.*(..))")
    fun execute(jointPoint: ProceedingJoinPoint): Any {
        var status: TraceStatus? = null

        try {
            val message = jointPoint.signature.toShortString()
            status = logTrace.begin(message)
            val result = jointPoint.proceed()
            logTrace.end(status)
            return result
        } catch (e: Exception) {
            logTrace.exception(status!!, e)
            throw e
        }
    }
}