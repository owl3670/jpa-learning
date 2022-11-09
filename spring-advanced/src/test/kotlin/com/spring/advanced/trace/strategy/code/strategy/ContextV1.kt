package com.spring.advanced.trace.strategy.code.strategy

class ContextV1 (
    private val strategy: Strategy
        ){
    fun execute(){
        val startTime = System.currentTimeMillis()
        strategy.call()
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime
        println("elapsedTime=$elapsedTime")
    }
}