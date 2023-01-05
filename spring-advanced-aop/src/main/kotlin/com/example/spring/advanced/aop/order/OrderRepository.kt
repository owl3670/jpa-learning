package com.example.spring.advanced.aop.order

import org.springframework.stereotype.Repository

@Repository
class OrderRepository {
    private val logger = mu.KotlinLogging.logger {}

    fun save(itemId: String): String {
        logger.info("[orderRepository] 실행")

        if(itemId.equals("ex")) {
            throw IllegalStateException("예외 발생")
        }
        return "ok"
    }
}