package com.example.spring.advanced.aop.internalcall

import org.springframework.stereotype.Component

@Component
class CallServiceV0 {
    private val logger = mu.KotlinLogging.logger {}

    fun external() {
        logger.info("call external")
        internal()
    }

    fun internal() {
        logger.info("call internal")
    }
}