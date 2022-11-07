package com.spring.advanced.trace.template.code

import mu.KotlinLogging

abstract class AbstractTemplate {
    private val logger = KotlinLogging.logger {}

    fun execute(){
        val startTime = System.currentTimeMillis()
        call()
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime
        logger.info("elapsedTime=$elapsedTime")
    }

    protected abstract fun call()
}