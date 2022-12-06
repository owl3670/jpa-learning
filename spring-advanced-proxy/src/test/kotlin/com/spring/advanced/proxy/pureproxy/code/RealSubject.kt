package com.spring.advanced.proxy.pureproxy.code

import mu.KotlinLogging

class RealSubject : Subject {
    private val logger = KotlinLogging.logger {}

    override fun operation(): String {
        logger.info("실제 객체 호출")
        sleep(1000)
        return "data"
    }

    private fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}