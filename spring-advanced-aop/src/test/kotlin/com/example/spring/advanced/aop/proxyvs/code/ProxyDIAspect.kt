package com.example.spring.advanced.aop.proxyvs.code

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

@Aspect
class ProxyDIAspect {
    private val logger = mu.KotlinLogging.logger {}

    @Before("execution(* com.example.spring.advanced.aop..*.*(..))")
    fun doTrace(joinPoint: JoinPoint) {
        logger.info("[proxyDIAdvice] {}", joinPoint)
    }
}