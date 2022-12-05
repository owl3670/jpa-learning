package com.spring.advanced.proxy.config.v2_dynamicproxy

import com.spring.advanced.proxy.app.v1.*
import com.spring.advanced.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler
import com.spring.advanced.proxy.config.v2_dynamicproxy.handler.LogTraceFilterHandler
import com.spring.advanced.proxy.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.lang.reflect.Proxy

@Configuration
class DynamicProxyFilterConfig {
    private val PATTERNS = arrayOf("request*", "order*", "save*")

    @Bean
    fun orderControllerV1(logTrace: LogTrace): OrderControllerV1 {
        val orderController: OrderControllerV1 = OrderControllerV1Impl(orderServiceV1(logTrace))

        return Proxy.newProxyInstance(
            DynamicProxyFilterConfig::class.java.classLoader,
            arrayOf(OrderControllerV1::class.java),
            LogTraceFilterHandler(orderController, logTrace, PATTERNS)
        ) as OrderControllerV1
    }

    @Bean
    fun orderServiceV1(logTrace: LogTrace): OrderServiceV1 {
        val orderService: OrderServiceV1 = OrderServiceV1Impl(orderRepositoryV1(logTrace))

        return Proxy.newProxyInstance(
            DynamicProxyFilterConfig::class.java.classLoader,
            arrayOf(OrderServiceV1::class.java),
            LogTraceFilterHandler(orderService, logTrace, PATTERNS)
        ) as OrderServiceV1
    }

    @Bean
    fun orderRepositoryV1(logTrace: LogTrace): OrderRepositoryV1 {
        val orderRepository: OrderRepositoryV1 = OrderRepositoryV1Impl()

        return Proxy.newProxyInstance(
            DynamicProxyFilterConfig::class.java.classLoader,
            arrayOf(OrderRepositoryV1::class.java),
            LogTraceFilterHandler(orderRepository, logTrace, PATTERNS)
        ) as OrderRepositoryV1
    }
}