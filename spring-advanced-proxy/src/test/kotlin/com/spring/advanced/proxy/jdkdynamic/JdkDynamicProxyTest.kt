package com.spring.advanced.proxy.jdkdynamic

import com.spring.advanced.proxy.jdkdynamic.code.*
import org.junit.jupiter.api.Test
import java.lang.reflect.Proxy

class JdkDynamicProxyTest {
    private val logger = mu.KotlinLogging.logger {}

    @Test
    fun dynamicA() {
        val target: AInterface = AImpl()
        val handler = TimeInvocationHandler(target)
        val proxy: AInterface = Proxy.newProxyInstance(
            AInterface::class.java.classLoader,
            arrayOf(AInterface::class.java),
            handler
        ) as AInterface
        proxy.call()
        logger.info("targetClass=${target.javaClass}")
        logger.info("proxyClass=${proxy.javaClass}")
    }

    @Test
    fun dynamicB() {
        val target: BInterface = BImpl()
        val handler = TimeInvocationHandler(target)
        val proxy: BInterface = Proxy.newProxyInstance(
            BInterface::class.java.classLoader,
            arrayOf(BInterface::class.java),
            handler
        ) as BInterface
        proxy.call()
        logger.info("targetClass=${target.javaClass}")
        logger.info("proxyClass=${proxy.javaClass}")
    }
}