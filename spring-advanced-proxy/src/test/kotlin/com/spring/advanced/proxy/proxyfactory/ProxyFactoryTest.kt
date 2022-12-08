package com.spring.advanced.proxy.proxyfactory

import com.spring.advanced.proxy.common.advice.TimeAdvice
import com.spring.advanced.proxy.common.service.ConcreteService
import com.spring.advanced.proxy.common.service.ServiceImpl
import com.spring.advanced.proxy.common.service.ServiceInterface
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.aop.framework.ProxyFactory
import org.assertj.core.api.Assertions.assertThat
import org.springframework.aop.support.AopUtils

class ProxyFactoryTest {
    private val logger = mu.KotlinLogging.logger {}

    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    fun interfaceProxy(){
        val target: ServiceInterface = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.addAdvice(TimeAdvice())
        val proxy: ServiceInterface = proxyFactory.proxy as ServiceInterface
        logger.info("targetClass=${target.javaClass}")
        logger.info("proxyClass=${proxy.javaClass}")

        proxy.save()

        assertThat(AopUtils.isAopProxy(proxy)).isTrue
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse
    }

    @Test
    @DisplayName("구체 클래스만 있으면 CGLIB 사용")
    fun concreteProxy(){
        val target: ConcreteService = ConcreteService()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.addAdvice(TimeAdvice())
        val proxy: ConcreteService = proxyFactory.proxy as ConcreteService
        logger.info("targetClass=${target.javaClass}")
        logger.info("proxyClass=${proxy.javaClass}")

        proxy.call()

        assertThat(AopUtils.isAopProxy(proxy)).isTrue
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue
    }

    @Test
    @DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB를 사용하고, 클래스 기반 프록시 사용")
    fun proxyTargetClass(){
        val target: ServiceInterface = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.isProxyTargetClass = true
        proxyFactory.addAdvice(TimeAdvice())

        val proxy: ServiceInterface = proxyFactory.proxy as ServiceInterface
        logger.info("targetClass=${target.javaClass}")
        logger.info("proxyClass=${proxy.javaClass}")

        proxy.save()

        assertThat(AopUtils.isAopProxy(proxy)).isTrue
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue
    }
}