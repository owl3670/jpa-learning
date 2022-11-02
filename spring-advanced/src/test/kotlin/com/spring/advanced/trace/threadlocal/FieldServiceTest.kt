package com.spring.advanced.trace.threadlocal

import com.spring.advanced.trace.threadlocal.code.FieldService
import mu.KotlinLogging
import org.junit.jupiter.api.Test

class FieldServiceTest {
    private val fieldService = FieldService()
    private val logger = KotlinLogging.logger {}

    @Test
    fun field() {
        val thread1 = Thread {
            fieldService.logic("userA")
        }
        val thread2 = Thread {
            fieldService.logic("userB")
        }
        thread1.name = "thread-A"
        thread2.name = "thread-B"

        thread1.start()
//        sleep(2000) // 동시성 문제 X
        sleep(100) // 동시성 문제 O
        thread2.start()

        sleep(3000)
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