package com.spring.advanced.proxy.code

import mu.KotlinLogging

class CacheProxy(
    private val subject: Subject
) : Subject {
    private val logger = KotlinLogging.logger {}
    private var cacheValue: String = ""

    override fun operation(): String {
        logger.info("프록시 호출")
        if (cacheValue.isEmpty()) {
            cacheValue = subject.operation()
        }
        return cacheValue
    }
}