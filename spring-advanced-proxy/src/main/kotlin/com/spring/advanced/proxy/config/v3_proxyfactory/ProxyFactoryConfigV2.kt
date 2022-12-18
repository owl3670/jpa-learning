package com.spring.advanced.proxy.config.v3_proxyfactory

import com.spring.advanced.proxy.app.v2.OrderControllerV2
import com.spring.advanced.proxy.app.v2.OrderRepositoryV2
import com.spring.advanced.proxy.app.v2.OrderServiceV2
import com.spring.advanced.proxy.config.v3_proxyfactory.advice.LogTraceAdvice
import com.spring.advanced.proxy.trace.logtrace.LogTrace
import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProxyFactoryConfigV2 {
    private val logger = mu.KotlinLogging.logger {}

    @Bean
    fun orderControllerV2(logTrace: LogTrace): OrderControllerV2 {
        val orderController = OrderControllerV2(orderServiceV2(logTrace))
        val factory = ProxyFactory(orderController)
        factory.addAdvisor(getAdvisor(logTrace))
        val proxy: OrderControllerV2 = factory.proxy as OrderControllerV2
        logger.info(
            "ProxyFactory proxy={}, target={}", proxy.javaClass,
            orderController.javaClass
        )
        return proxy
    }

    @Bean
    fun orderServiceV2(logTrace: LogTrace): OrderServiceV2 {
        val orderService = OrderServiceV2(orderRepositoryV2(logTrace))
        val factory = ProxyFactory(orderService)
        factory.addAdvisor(getAdvisor(logTrace))
        val proxy: OrderServiceV2 = factory.proxy as OrderServiceV2
        logger.info(
            "ProxyFactory proxy={}, target={}", proxy.javaClass,
            orderService.javaClass
        )
        return proxy
    }

    @Bean
    fun orderRepositoryV2(logTrace: LogTrace): OrderRepositoryV2 {
        val orderRepository = OrderRepositoryV2()
        val factory = ProxyFactory(orderRepository)
        factory.addAdvisor(getAdvisor(logTrace))
        val proxy: OrderRepositoryV2 = factory.proxy as OrderRepositoryV2
        logger.info(
            "ProxyFactory proxy={}, target={}", proxy.javaClass,
            orderRepository.javaClass
        )
        return proxy
    }

    private fun getAdvisor(logTrace: LogTrace): Advisor {
        //pointcut
        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedNames("request*", "order*", "save*")
        //advice
        val advice = LogTraceAdvice(logTrace)
        //advisor = pointcut + advice
        return DefaultPointcutAdvisor(pointcut, advice)
    }
}