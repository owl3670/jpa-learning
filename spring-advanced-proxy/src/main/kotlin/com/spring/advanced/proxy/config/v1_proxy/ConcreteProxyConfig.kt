package com.spring.advanced.proxy.config.v1_proxy

import com.spring.advanced.proxy.app.v2.OrderControllerV2
import com.spring.advanced.proxy.app.v2.OrderRepositoryV2
import com.spring.advanced.proxy.app.v2.OrderServiceV2
import com.spring.advanced.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy
import com.spring.advanced.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy
import com.spring.advanced.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy
import com.spring.advanced.proxy.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ConcreteProxyConfig {
    @Bean
    fun orderControllerV2(logTrace: LogTrace): OrderControllerV2 {
        val controller = OrderControllerV2(orderServiceV2(logTrace))
        return OrderControllerConcreteProxy(
            controller,
            logTrace
        )
    }

    @Bean
    fun orderServiceV2(logTrace: LogTrace): OrderServiceV2 {
        val service = OrderServiceV2(orderRepositoryV2(logTrace))
        return OrderServiceConcreteProxy(
            service,
            logTrace
        )
    }

    @Bean
    fun orderRepositoryV2(logTrace: LogTrace): OrderRepositoryV2 {
        val repository = OrderRepositoryV2()
        return OrderRepositoryConcreteProxy(
            repository,
            logTrace
        )
    }
}