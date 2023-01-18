package com.example.spring.advanced.aop.order.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

@Aspect
class AspectV3 {
    private val logger = mu.KotlinLogging.logger {}

    @Pointcut("execution(* com.example.spring.advanced.aop.order..*(..))")
    fun allOrder() {
    }

    @Pointcut("execution(* *..*Service.*(..))")
    private fun allService() {
    }

    @Around("allOrder()")
    fun doLog(jointPoint: ProceedingJoinPoint): Any {
        logger.info("[log] ${jointPoint.signature}")
        return jointPoint.proceed()
    }

    @Around("allOrder() && allService()")
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