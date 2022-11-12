package com.spring.advanced.trace.strategy.code.template

import mu.KotlinLogging

class TimeLogTemplate {
    private val logger = KotlinLogging.logger {}

    fun execute(callback: Callback) {
        val startTime = System.currentTimeMillis()
        callback.call()
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime
        logger.info("elapsedTime=$elapsedTime")
    }
}