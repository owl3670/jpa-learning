package com.example.spring.advanced.aop.order.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

@Aspect
class AspectV1 {
    private val logger = mu.KotlinLogging.logger {}

    @Around("execution(* com.example.spring.advanced.aop.order..*(..))")
    fun doLog(jointPoint: ProceedingJoinPoint): Any {
        logger.info("[log] ${jointPoint.signature}")
        return jointPoint.proceed()
    }
}