package com.spring.advanced.proxy.common.service

open class ConcreteService {
    private val logger = mu.KotlinLogging.logger {}

    open fun call() {
        logger.info("ConcreteService 호출")
    }
}