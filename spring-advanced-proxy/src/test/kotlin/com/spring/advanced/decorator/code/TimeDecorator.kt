package com.spring.advanced.decorator.code

import mu.KotlinLogging

class TimeDecorator(
    private val component: Component
): Component {
    private val logger = KotlinLogging.logger {  }

    override fun operation(): String {
        logger.info("TimeDecorator 실행")
        val startTime = System.currentTimeMillis()
        val result = component.operation()
        val endTime = System.currentTimeMillis()
        val time = endTime - startTime
        logger.info("TimeDecorator 종료 resultTime=$time")
        return result
    }
}