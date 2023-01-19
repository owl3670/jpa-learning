package com.example.spring.advanced.aop.order.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

@Aspect
class AspectV4Pointcut {
    private val logger = mu.KotlinLogging.logger {}

    @Around("com.example.spring.advanced.aop.order.aop.Pointcuts.allOrder()")
    fun doLog(jointPoint: ProceedingJoinPoint): Any {
        logger.info("[log] ${jointPoint.signature}")
        return jointPoint.proceed()
    }

    @Around("com.example.spring.advanced.aop.order.aop.Pointcuts.orderAndService()")
    fun doTransaction(joinPoint: ProceedingJoinPoint): Any {
        try {
            logger.info("[트랜잭션 시작] {}", joinPoint.signature)
            val result = joinPoint.proceed()
            logger.info("[트랜잭션 종료] {}", joinPoint.signature)
            return result
        } catch (e: Exception) {
            logger.info("[트랜잭션 예외] {}", joinPoint.signature)
            throw e
        } finally {
            logger.info("[리소스 릴리즈] {}", joinPoint.signature)
        }
    }
}