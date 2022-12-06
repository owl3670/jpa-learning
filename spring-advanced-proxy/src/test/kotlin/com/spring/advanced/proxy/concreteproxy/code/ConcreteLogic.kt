package com.spring.advanced.proxy.concreteproxy.code

import mu.KotlinLogging

open class ConcreteLogic {
    private val logger = KotlinLogging.logger {}

    open fun operation(): String {
        logger.info("ConcreteLogic 실행")
        return "data"
    }
}