package com.spring.advanced.trace.template

import com.spring.advanced.trace.template.code.SubClassLogic1
import com.spring.advanced.trace.template.code.SubClassLogic2
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

    @Test
    fun templateMethodV1() {
        val logic1 = SubClassLogic1()
        logic1.execute()
        val logic2 = SubClassLogic2()
        logic2.execute()
    }
}