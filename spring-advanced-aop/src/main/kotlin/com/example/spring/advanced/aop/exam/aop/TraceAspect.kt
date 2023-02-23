package com.example.spring.advanced.aop.exam.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

@Aspect
class TraceAspect {
    private val logger = mu.KotlinLogging.logger {}

    @Before("@annotation(com.example.spring.advanced.aop.exam.annotation.Trace)")
    fun doTrace(joinPoint: JoinPoint) {
        val args = joinPoint.args
        logger.info("[trace] {}, args={}", joinPoint.signature, args)
    }
}