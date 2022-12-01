package com.spring.advanced.jdkdynamic

import mu.KotlinLogging
import org.junit.jupiter.api.Test
import java.lang.reflect.Method

class ReflectionTest {
    private val logger = KotlinLogging.logger {  }

    @Test
    fun reflection0(){
        val target = Hello()

        logger.info("start")
        val result1 = target.callA()
        logger.info("result1={}", result1)

        logger.info("start")
        val result2 = target.callB()
        logger.info("result2={}", result2)
    }

    @Test
    fun reflection1(){
        val classHello = Hello::class.java

        val target = Hello()

        val methodCallA = classHello.getMethod("callA")
        val result1 = methodCallA.invoke(target)
        logger.info("result1={}", result1)

        val methodCallB = classHello.getMethod("callB")
        val result2 = methodCallB.invoke(target)
        logger.info("result2={}", result2)
    }

    @Test
    fun reflection2(){
        val classHello = Hello::class.java

        val target = Hello()

        val methodCallA = classHello.getMethod("callA")
        dynamicCall(methodCallA, target)

        val methodCallB = classHello.getMethod("callB")
        dynamicCall(methodCallB, target)
    }

    private fun dynamicCall(method: Method, target: Any){
        logger.info("start")
        val result = method.invoke(target)
        logger.info("result={}", result)
    }

    class Hello{
        private val logger = KotlinLogging.logger {  }

        fun callA(): String{
            logger.info("callA")
            return "A"
        }

        fun callB(): String{
            logger.info("callB")
            return "B"
        }
    }
}