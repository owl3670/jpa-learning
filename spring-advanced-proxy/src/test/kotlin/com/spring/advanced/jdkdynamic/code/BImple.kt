package com.spring.advanced.jdkdynamic.code

class BImpl : BInterface {
    private val logger = mu.KotlinLogging.logger {  }

    override fun call(): String {
        logger.info("B 호출")
        return "b"
    }
}