package com.spring.advanced.trace.strategy

import mu.KotlinLogging
import org.junit.jupiter.api.Test

class ContextV1Test {
    private val logger = KotlinLogging.logger {}

    @Test
    fun strategyV0(){
        this.logic1()
        this.logic2()
    }

    private fun logic1() {
        val startTime = System.currentTimeMillis()
        logger.info("execute logic1")
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime
        logger.info("elapsedTime: $elapsedTime")
    }

    private fun logic2() {
        val startTime = System.currentTimeMillis()
        logger.info("execute logic2")
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime
        logger.info("elapsedTime: $elapsedTime")
    }
}