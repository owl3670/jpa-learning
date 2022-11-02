package com.spring.advanced.trace.threadlocal.code

import mu.KotlinLogging

class FieldService {
    private var nameStore: String = ""
    private val logger = KotlinLogging.logger {}

    fun logic(name: String): String {
        logger.info("저장 name={} -> nameStore={}", name, nameStore)
        nameStore = name
        sleep(1000)
        logger.info("조회 nameStore={}", nameStore)
        return nameStore
    }

    private fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}