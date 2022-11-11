package com.spring.advanced.trace.strategy

import com.spring.advanced.trace.strategy.code.strategy.ContextV1
import com.spring.advanced.trace.strategy.code.strategy.Strategy
import com.spring.advanced.trace.strategy.code.strategy.StrategyLogic1
import com.spring.advanced.trace.strategy.code.strategy.StrategyLogic2
import mu.KotlinLogging
import org.junit.jupiter.api.Test

class ContextV1Test {
    private val logger = KotlinLogging.logger {}

    @Test
    fun strategyV0() {
        this.logic1()
        this.logic2()
    }

    private fun logic1() {
        val startTime = System.currentTimeMillis()
        logger.info("execute logic1")
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime
        logger.info("elapsedTime=$elapsedTime")
    }

    private fun logic2() {
        val startTime = System.currentTimeMillis()
        logger.info("execute logic2")
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime
        logger.info("elapsedTime=$elapsedTime")
    }

    @Test
    fun strategyV1() {
        val context1 = ContextV1(StrategyLogic1())
        context1.execute()
        val context2 = ContextV1(StrategyLogic2())
        context2.execute()
    }

    @Test
    fun strategyV2() {
        val strategyLogic1 = object : Strategy {
            override fun call() {
                logger.info("execute logic1")
            }
        }
        logger.info("strategyLogic1=${strategyLogic1.javaClass}")
        val context1 = ContextV1(strategyLogic1)
        context1.execute()

        val strategyLogic2 = object : Strategy {
            override fun call() {
                logger.info("execute logic2")
            }
        }
        logger.info("strategyLogic2=${strategyLogic2.javaClass}")
        val context2 = ContextV1(strategyLogic2)
        context2.execute()
    }

    @Test
    fun strategyV3() {
        val context1 = ContextV1(object : Strategy {
            override fun call() {
                logger.info("execute logic1")
            }
        })
        context1.execute()

        val context2 = ContextV1(object : Strategy {
            override fun call() {
                logger.info("execute logic2")
            }
        })
        context2.execute()
    }

    @Test
    fun strategyV4() {
        val context1 = ContextV1 { logger.info("execute logic1") }
        context1.execute()

        val context2 = ContextV1 { logger.info("execute logic2") }
        context2.execute()
    }
}