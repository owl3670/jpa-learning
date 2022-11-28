package com.spring.advanced.concreteproxy.code

import mu.KotlinLogging

class ConcreteLogic {
    private val logger = KotlinLogging.logger {}

    fun operation(): String {
        logger.info("ConcreteLogic 실행")
        return "data"
    }
}