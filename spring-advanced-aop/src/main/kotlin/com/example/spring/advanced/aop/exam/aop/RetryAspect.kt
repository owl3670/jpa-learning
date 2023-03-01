package com.example.spring.advanced.aop.exam.aop

import com.example.spring.advanced.aop.exam.annotation.Retry
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

@Aspect
class RetryAspect {
    private val logger = mu.KotlinLogging.logger {}

    @Around("@annotation(retry)")
    fun doRetry(joinPoint: ProceedingJoinPoint, retry: Retry): Any {
        logger.info("[retry] {} retry={}", joinPoint.signature.name, retry)

        val maxRetry = retry.value
        var exceptionHolder: Exception? = null

        for (retryCount in 1..maxRetry) {
            try {
                logger.info("[retry] {} try count={}/{}", joinPoint.signature.name, retryCount, maxRetry)
                return joinPoint.proceed()
            } catch (e: Exception) {
                exceptionHolder = e
            }
        }
        throw exceptionHolder!!
    }
}