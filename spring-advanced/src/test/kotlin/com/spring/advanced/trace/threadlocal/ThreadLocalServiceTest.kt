package com.spring.advanced.trace.threadlocal

import com.spring.advanced.trace.threadlocal.code.ThreadLocalService
import mu.KotlinLogging
import org.junit.jupiter.api.Test

class ThreadLocalServiceTest {
    private val logger = KotlinLogging.logger {}
    private val threadLocalService = ThreadLocalService()

    @Test
    fun threadLocalTest() {
        logger.info("main start")
        val thread1 = Thread {
            threadLocalService.logic("userA")
        }
        val thread2 = Thread {
            threadLocalService.logic("userB")
        }
        thread1.name = "thread-A"
        thread2.name = "thread-B"

        thread1.start()
        sleep(100)
        thread2.start()

        sleep(2000)
        logger.info("main exit")
    }

    private fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}