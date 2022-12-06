package com.spring.advanced.proxy.common.service

class ServiceImpl : ServiceInterface {
    private val logger = mu.KotlinLogging.logger {}

    override fun save() {
        logger.info("save 호출")
    }

    override fun find() {
        logger.info("find 호출")
    }
}