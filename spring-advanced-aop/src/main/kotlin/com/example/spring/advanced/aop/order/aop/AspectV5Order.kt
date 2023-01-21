package com.example.spring.advanced.aop.order.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.core.annotation.Order

class AspectV5Order {
    private val logger = mu.KotlinLogging.logger {}

    @Aspect
    @Order(2)
    class LogAspect {
        private val logger = mu.KotlinLogging.logger {}

        @Around("com.example.spring.advanced.aop.order.aop.Pointcuts.allOrder()")
        fun doLog(jointPoint: ProceedingJoinPoint): Any {
            logger.info("[log] ${jointPoint.signature}")
            return jointPoint.proceed()
        }
    }

    @Aspect
    @Order(1)
    class TxAspect {
        private val logger = mu.KotlinLogging.logger {}

        @Around("com.example.spring.advanced.aop.order.aop.Pointcuts.orderAndService()")
        fun doTransaction(jointPoint: ProceedingJoinPoint): Any {
            try{
                logger.info("[트랜잭션 시작] {}", jointPoint.signature)
                val result = jointPoint.proceed()
                logger.info("[트랜잭션 커밋] {}", jointPoint.signature)
                return result
            }catch (e: Exception){
                logger.info("[트랜잭션 롤백] {}", jointPoint.signature)
                throw e
            }finally {
                logger.info("[리소스 릴리즈] {}", jointPoint.signature)
            }
        }
    }
}