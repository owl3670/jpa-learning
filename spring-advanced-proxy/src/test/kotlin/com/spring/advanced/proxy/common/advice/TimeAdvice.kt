package com.spring.advanced.proxy.common.advice

import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation

class TimeAdvice: MethodInterceptor {
    private val logger = mu.KotlinLogging.logger {}

    override fun invoke(invocation: MethodInvocation): Any? {
        logger.info("TimeProxy 실행")
        val start = System.currentTimeMillis()
        val result = invocation.proceed()
        val end = System.currentTimeMillis()
        val resultTime = end - start
        logger.info("TimeProxy 종료 resultTime=$resultTime")
        return result
    }
}