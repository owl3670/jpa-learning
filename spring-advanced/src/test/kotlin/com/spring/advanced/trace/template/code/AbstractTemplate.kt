package com.spring.advanced.trace.template.code

abstract class AbstractTemplate {
    fun execute(){
        val startTime = System.currentTimeMillis()
        call()
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime
        println("elapsedTime=$elapsedTime")
    }

    protected abstract fun call()
}