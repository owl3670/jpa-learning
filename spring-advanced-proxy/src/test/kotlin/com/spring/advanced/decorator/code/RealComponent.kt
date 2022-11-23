package com.spring.advanced.decorator.code

import mu.KotlinLogging

class RealComponent : Component {
    private val logger = KotlinLogging.logger {}

    override fun operation(): String {
        logger.info("RealComponent 실행")
        return "data"
    }
}