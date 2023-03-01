package com.example.spring.advanced.aop.internalcall

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

@Aspect
class CallLogAspect {
    private val logger = mu.KotlinLogging.logger {}

    @Before("execution(* com.example.spring.advanced.aop.internalcall..*.*(..))")
    fun doLog(joinPoint: JoinPoint) {
        logger.info("aop={}", joinPoint.signature)
    }
}