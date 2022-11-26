package com.spring.advanced.proxy.config.v1_proxy

import com.spring.advanced.proxy.app.v1.*
import com.spring.advanced.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy
import com.spring.advanced.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy
import com.spring.advanced.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy
import com.spring.advanced.proxy.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InterfaceProxyConfig {
    @Bean
    fun orderController(logTrace: LogTrace): OrderControllerV1 {
        val controllerImpl = OrderControllerV1Impl(orderService(logTrace))
        return OrderControllerInterfaceProxy(
            controllerImpl,
            logTrace
        )
    }

    @Bean
    fun orderService(logTrace: LogTrace): OrderServiceV1 {
        val serviceImpl = OrderServiceV1Impl(orderRepository(logTrace))
        return OrderServiceInterfaceProxy(
            serviceImpl,
            logTrace
        )
    }

    @Bean
    fun orderRepository(logTrace: LogTrace): OrderRepositoryV1 {
        val repositoryImpl = OrderRepositoryV1Impl()
        return OrderRepositoryInterfaceProxy(
            repositoryImpl,
            logTrace
        )
    }
}