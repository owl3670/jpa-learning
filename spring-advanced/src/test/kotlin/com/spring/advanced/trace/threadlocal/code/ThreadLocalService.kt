package com.spring.advanced.trace.threadlocal.code

import mu.KotlinLogging

class ThreadLocalService {
    private val logger = KotlinLogging.logger {}
    private val nameStore: ThreadLocal<String> = ThreadLocal()

    fun logic(name: String): String{
        logger.info { "저장 name=$name -> nameStore=${nameStore.get()}" }
        nameStore.set(name)
        sleep(1000)
        logger.info { "조회 nameStore=${nameStore.get()}" }
        return nameStore.get()
    }

    private fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}