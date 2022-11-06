package com.spring.advanced.trace.template

import mu.KotlinLogging
import org.junit.jupiter.api.Test

class TemplateMethodTest {
    private val logger = KotlinLogging.logger {}

    @Test
    fun templateMethodV0() {
        this.logic1()
        this.logic2()
    }

    private fun logic1(){
        val startTime = System.currentTimeMillis()
        logger.info("logic1 start")
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime
        logger.info("elapsedTime=$elapsedTime")
    }

    private fun logic2(){
        val startTime = System.currentTimeMillis()
        logger.info("logic2 start")
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime
        logger.info("elapsedTime=$elapsedTime")
    }
}