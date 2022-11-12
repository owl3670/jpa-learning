package com.spring.advanced.trace.strategy.code

import com.spring.advanced.trace.strategy.code.template.Callback
import com.spring.advanced.trace.strategy.code.template.TimeLogTemplate
import org.junit.jupiter.api.Test

class TemplateCallbackTest {
    @Test
    fun callbackV1(){
        val template = TimeLogTemplate()
        template.execute(object : Callback {
            override fun call() {
                println("execute logic1")
            }
        })
        template.execute(object : Callback {
            override fun call() {
                println("execute logic2")
            }
        })
    }

    @Test
    fun callbackV2(){
        val template = TimeLogTemplate()
        template.execute { println("execute logic1") }
        template.execute { println("execute logic2") }
    }
}