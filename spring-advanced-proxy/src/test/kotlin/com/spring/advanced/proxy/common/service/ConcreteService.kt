package com.spring.advanced.proxy.common.service

open class ConcreteService {
    private val logger = mu.KotlinLogging.logger {}

    fun call() {
        logger.info("ConcreteService 호출")
    }
}