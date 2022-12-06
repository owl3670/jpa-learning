package com.spring.advanced.proxy.jdkdynamic.code

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class TimeInvocationHandler(
    private val target: Any
) : InvocationHandler {
    private val logger = mu.KotlinLogging.logger {}

    override fun invoke(proxy: Any, method: Method, args: Array<Any>?): Any {
        logger.info("TimeProxy 실행")
        val start = System.currentTimeMillis()
        val result = method.invoke(target, *args ?: arrayOf())
        val end = System.currentTimeMillis()
        val resultTime = end - start
        logger.info("TimeProxy 종료 resultTime=$resultTime")
        return result
    }
}