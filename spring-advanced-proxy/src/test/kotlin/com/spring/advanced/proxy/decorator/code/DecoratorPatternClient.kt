package com.spring.advanced.proxy.decorator.code

import mu.KotlinLogging

class DecoratorPatternClient(
    private val component: Component
) {
    private val logger = KotlinLogging.logger {}

    fun execute(){
        val result = component.operation()
        logger.info("result={}", result)
    }
}