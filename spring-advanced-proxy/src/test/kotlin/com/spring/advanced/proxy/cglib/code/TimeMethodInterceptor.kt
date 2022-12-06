package com.spring.advanced.proxy.cglib.code

import org.springframework.cglib.proxy.MethodInterceptor
import org.springframework.cglib.proxy.MethodProxy
import java.lang.reflect.Method

class TimeMethodInterceptor(
    private val target: Any
): MethodInterceptor {
    private val logger = mu.KotlinLogging.logger {}

    override fun intercept(obj: Any?, method: Method?, args: Array<out Any>?, proxy: MethodProxy?): Any? {
        logger.info("TimeProxy 실행")
        val start = System.currentTimeMillis()
        val result = proxy?.invoke(target, args)
        val end = System.currentTimeMillis()
        val resultTime = end - start
        logger.info("TimeProxy 종료 resultTime=$resultTime")
        return result
    }
}