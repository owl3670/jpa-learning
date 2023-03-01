package com.example.spring.advanced.aop.pointcut

import com.example.spring.advanced.aop.order.OrderService
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(BeanTest.BeanAspect::class)
@SpringBootTest
class BeanTest {
    @Autowired
    lateinit var orderService: OrderService

    @Test
    fun success() {
        orderService.orderItem("itemA")
    }

    @Aspect
    class BeanAspect {
        private val logger = mu.KotlinLogging.logger {}

        @Around("bean(orderService) || bean(*Repository)")
        fun doLog(joinPoint: ProceedingJoinPoint): Any {
            logger.info("[bean] {}", joinPoint.signature)
            return joinPoint.proceed()
        }
    }
}