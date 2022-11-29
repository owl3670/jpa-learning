package com.spring.advanced.concreteproxy.code

import mu.KotlinLogging

class TimeProxy(
    private val logic: ConcreteLogic
) : ConcreteLogic(){
    private val logger = KotlinLogging.logger {}

    override fun operation(): String {
        logger.info("TimeDecorator 실행")
        val start = System.currentTimeMillis()
        val result = logic.operation()
        val end = System.currentTimeMillis()
        val resultTime = end - start
        logger.info("TimeDecorator 종료 resultTime=$resultTime")
        return result
    }
}