package com.example.spring.advanced.aop.pointcut

import com.example.spring.advanced.aop.member.MemberService
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(ThisTargetTest.ThisTargetAspect::class)
//@SpringBootTest(properties = ["spring.aop.proxy-target-class=false"])
@SpringBootTest(properties = ["spring.aop.proxy-target-class=true"])
class ThisTargetTest {
    private val logger = mu.KotlinLogging.logger {}

    @Autowired
    lateinit var memberService: MemberService

    @Test
    fun success() {
        logger.info("memberService Proxy={}", memberService.javaClass)
        memberService.hello("helloA")
    }

    @Aspect
    class ThisTargetAspect{
        private val logger = mu.KotlinLogging.logger {}

        // 부모 타입 허용
        @Around("this(com.example.spring.advanced.aop.member.MemberService)")
        fun doThisInterface(joinPoint: ProceedingJoinPoint): Any {
            logger.info("[this-interface] {}", joinPoint.signature)
            return joinPoint.proceed()
        }

        // 부모 타입 허용
        @Around("this(com.example.spring.advanced.aop.member.MemberService)")
        fun doTargetInterface(joinPoint: ProceedingJoinPoint): Any {
            logger.info("[target-interface] {}", joinPoint.signature)
            return joinPoint.proceed()
        }

        // this: 스프링 AOP 프록시 객체 대상
        // JDK 동적 프록시는 인터페이스를 기반으로 생성되므로 구현 클래스를 알 수 없음
        // CGLIB 프록시는 구현 클래스를 기반으로 생성되므로 구현 클래스를 알 수 있음
        @Around("this(com.example.spring.advanced.aop.member.MemberServiceImpl)")
        fun doThis(joinPoint: ProceedingJoinPoint): Any {
            logger.info("[this-impl] {}", joinPoint.signature)
            return joinPoint.proceed()
        }

        // target: 실제 target 객체 대상
        @Around("target(com.example.spring.advanced.aop.member.MemberServiceImpl)")
        fun doTarget(joinPoint: ProceedingJoinPoint): Any {
            logger.info("[target-impl] {}", joinPoint.signature)
            return joinPoint.proceed()
        }
    }
}