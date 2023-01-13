package com.example.spring.advanced.aop.order.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

@Aspect
class AspectV2 {
    private val logger = mu.KotlinLogging.logger {}

    @Pointcut("execution(* com.example.spring.advanced.aop.order..*(..))")
    fun allOrder(){}

    @Around("allOrder()")
    fun doLog(jointPoint: ProceedingJoinPoint): Any {
        logger.info("[log] ${jointPoint.signature}")
        return jointPoint.proceed()
    }
}