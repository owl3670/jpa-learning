package com.spring.advanced.trace.strategy

import com.spring.advanced.trace.strategy.code.strategy.ContextV2
import com.spring.advanced.trace.strategy.code.strategy.Strategy
import com.spring.advanced.trace.strategy.code.strategy.StrategyLogic1
import com.spring.advanced.trace.strategy.code.strategy.StrategyLogic2
import org.junit.jupiter.api.Test

class ContextV2Test {
    @Test
    fun strategyV1(){
        val context = ContextV2()
        context.execute(StrategyLogic1())
        context.execute(StrategyLogic2())
    }

    @Test
    fun strategyV2(){
        val context = ContextV2()
        context.execute(object : Strategy {
            override fun call() {
                println("StrategyLogic1 call")
            }
        })
        context.execute(object : Strategy {
            override fun call() {
                println("StrategyLogic2 call")
            }
        })
    }

    @Test
    fun strategyV3(){
        val context = ContextV2()
        context.execute { println("StrategyLogic1 call") }
        context.execute { println("StrategyLogic2 call") }
    }
}