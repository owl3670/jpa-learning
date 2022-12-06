package com.spring.advanced.proxy.cglib

import com.spring.advanced.proxy.cglib.code.TimeMethodInterceptor
import com.spring.advanced.proxy.common.service.ConcreteService
import org.junit.jupiter.api.Test
import org.springframework.cglib.proxy.Enhancer

class CglibTest {
    private val logger = mu.KotlinLogging.logger {}

    @Test
    fun cglib(){
        val target = ConcreteService()
        val enhancer = Enhancer()
        enhancer.setSuperclass(target.javaClass)
        enhancer.setCallback(TimeMethodInterceptor(target))
        val proxy = enhancer.create() as ConcreteService
        logger.info("targetClass=${target.javaClass}")
        logger.info("proxyClass=${proxy.javaClass}")
        proxy.call()
    }
}