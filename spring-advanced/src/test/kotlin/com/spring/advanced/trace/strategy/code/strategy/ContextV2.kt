package com.spring.advanced.trace.strategy.code.strategy

import mu.KotlinLogging

class ContextV2 {
    private val logger = KotlinLogging.logger("ContextV2")

    fun execute(strategy: Strategy) {
        val startTime = System.currentTimeMillis()
        strategy.call()
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime
        logger.info("elapsedTime=$elapsedTime")
    }
}