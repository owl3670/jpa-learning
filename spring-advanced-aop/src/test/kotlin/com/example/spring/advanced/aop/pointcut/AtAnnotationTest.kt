package com.example.spring.advanced.aop.pointcut

import com.example.spring.advanced.aop.member.MemberService
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(AtAnnotationTest.AtAnnotationAspect::class)
@SpringBootTest
class AtAnnotationTest {
    @Autowired
    lateinit var memberService: MemberService

    private val logger = mu.KotlinLogging.logger {}

    @Test
    fun success() {
        logger.info("memberService Proxy={}", memberService.javaClass)
        memberService.hello("helloA")
    }

    @Aspect
    class AtAnnotationAspect {
        private val logger = mu.KotlinLogging.logger {}

        @Around("@annotation(com.example.spring.advanced.aop.member.annotation.MethodAop)")
        fun doAtAnnotation(joinPoint: ProceedingJoinPoint): Any {
            logger.info("[@annotation] {}", joinPoint.signature)
            return joinPoint.proceed()
        }
    }
}