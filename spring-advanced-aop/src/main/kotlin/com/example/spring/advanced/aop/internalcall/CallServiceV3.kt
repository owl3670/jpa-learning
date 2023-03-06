package com.example.spring.advanced.aop.internalcall

import org.springframework.stereotype.Component

@Component
class CallServiceV3(
    val intervalService: InternalService
) {
    private val logger = mu.KotlinLogging.logger {}

    fun external() {
        logger.info("call external")
        intervalService.internal()
    }
}