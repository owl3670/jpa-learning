package com.example.spring.advanced.aop.order

import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository
) {
    private val logger = mu.KotlinLogging.logger {}

    fun orderItem(itemId: String): String {
        logger.info("[orderService] 실행")
        return orderRepository.save(itemId)
    }
}