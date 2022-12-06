package com.spring.advanced.proxy.decorator.code

import mu.KotlinLogging

class MessageDecorator(
    private val component: Component
): Component {
    private val logger = KotlinLogging.logger {  }
    override fun operation(): String {
        logger.info("MessageDecorator 실행")

        val result = component.operation()
        val decoResult = "***** $result *****"
        logger.info("MessageDecorator 꾸미기 적용 전=$result, 적용 후=$decoResult")

        return decoResult
    }
}