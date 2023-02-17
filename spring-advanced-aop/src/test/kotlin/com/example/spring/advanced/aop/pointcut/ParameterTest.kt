package com.example.spring.advanced.aop.pointcut

import com.example.spring.advanced.aop.member.MemberService
import com.example.spring.advanced.aop.member.annotation.ClassAop
import com.example.spring.advanced.aop.member.annotation.MethodAop
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(ParameterTest.ParameterAspect::class)
@SpringBootTest
class ParameterTest {
    private val logger = mu.KotlinLogging.logger {}

    @Autowired
    lateinit var memberService: MemberService

    @Test
    fun success() {
        logger.info("memberService Proxy={}", memberService.javaClass)
        memberService.hello("helloA")
    }

    @Aspect
    class ParameterAspect {
        private val logger = mu.KotlinLogging.logger {}

        @Pointcut("execution(* com.example.spring.advanced.aop.member..*.*(..))")
        private fun allMember() {}

        @Around("allMember()")
        fun logArgs1(joinPoint: ProceedingJoinPoint): Any {
            val arg1 = joinPoint.args[0]
            logger.info("[logArgs1] {}, arg={}", joinPoint.signature, arg1)
            return joinPoint.proceed()
        }

        @Around("allMember() && args(arg,..)")
        fun logArgs2(joinPoint: ProceedingJoinPoint, arg: Any): Any {
            logger.info("[logArgs2] {}, arg={}", joinPoint.signature, arg)
            return joinPoint.proceed()
        }

        @Before("allMember() && args(arg,..)")
        fun logArgs3(arg: String) {
            logger.info("[logArgs3] arg={}", arg)
        }

        @Before("allMember() && this(obj)")
        fun thisArgs(joinPoint: JoinPoint, obj: MemberService){
            logger.info("[this] {}, obj={}", joinPoint.signature, obj.javaClass)
        }

        @Before("allMember() && target(obj)")
        fun targetArgs(joinPoint: JoinPoint, obj: MemberService){
            logger.info("[target] {}, obj={}", joinPoint.signature, obj.javaClass)
        }

        @Before("allMember() && @target(annotation)")
        fun atTarget(joinPoint: JoinPoint, annotation: ClassAop){
            logger.info("[@target] {}, obj={}", joinPoint.signature, annotation)
        }

        @Before("allMember() && @within(annotation)")
        fun atWithin(joinPoint: JoinPoint, annotation: ClassAop){
            logger.info("[@within] {}, obj={}", joinPoint.signature, annotation)
        }

        @Before("allMember() && @annotation(annotation)")
        fun atAnnotation(joinPoint: JoinPoint, annotation: MethodAop){
            logger.info("[@annotation] {}, obj={}", joinPoint.signature, annotation.value)
        }
    }
}