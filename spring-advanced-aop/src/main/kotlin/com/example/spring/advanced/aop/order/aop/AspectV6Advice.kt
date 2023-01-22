package com.example.spring.advanced.aop.order.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*

@Aspect
class AspectV6Advice {
    private val logger = mu.KotlinLogging.logger {}

    @Around("com.example.spring.advanced.aop.order.aop.Pointcuts.orderAndService()")
    fun doTransaction(joinPoint: ProceedingJoinPoint): Any{
        try{
            logger.info("[트랜잭션 시작] {}", joinPoint.signature)
            val result = joinPoint.proceed()
            logger.info("[트랜잭션 커밋] {}", joinPoint.signature)
            return result
        }catch (e: Exception){
            logger.info("[트랜잭션 롤백] {}", joinPoint.signature)
            throw e
        }finally {
            logger.info("[리소스 릴리즈] {}", joinPoint.signature)
        }
    }

    @Before("com.example.spring.advanced.aop.order.aop.Pointcuts.orderAndService()")
    fun doBefore(joinPoint: JoinPoint){
        logger.info("[before] {}", joinPoint.signature)
    }

    @AfterReturning(value = "com.example.spring.advanced.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    fun doReturn(joinPoint: JoinPoint, result: Any){
        logger.info("[return] {} return={}", joinPoint.signature, result)
    }

    @AfterThrowing(value = "com.example.spring.advanced.aop.order.aop.Pointcuts.orderAndService()", throwing = "e")
    fun doThrowing(joinPoint: JoinPoint, e: Exception){
        logger.info("[ex] {} message={}", joinPoint.signature, e.message)
    }

    @After(value = "com.example.spring.advanced.aop.order.aop.Pointcuts.orderAndService()")
    fun doAfter(joinPoint: JoinPoint){
        logger.info("[after] {}", joinPoint.signature)
    }
}