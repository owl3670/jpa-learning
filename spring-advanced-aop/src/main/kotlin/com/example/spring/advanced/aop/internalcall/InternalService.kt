package com.example.spring.advanced.aop.internalcall

import org.springframework.stereotype.Component

@Component
class InternalService {
    private val logger = mu.KotlinLogging.logger {}

    fun  internal() {
        logger.info("call internal")
    }
}
