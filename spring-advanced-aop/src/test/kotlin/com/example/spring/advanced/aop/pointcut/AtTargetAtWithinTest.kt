package com.example.spring.advanced.aop.pointcut

import com.example.spring.advanced.aop.member.annotation.ClassAop
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Import(AtTargetAtWithinTest.Config::class)
@SpringBootTest
class AtTargetAtWithinTest {
    private val logger = mu.KotlinLogging.logger {}

    @Autowired
    lateinit var child: Child

    @Test
    fun success() {
        logger.info("child Proxy={}", child.javaClass)
        child.childMethod()
        child.parentMethod()
    }

    @Configuration
    class Config {
        @Bean
        fun parent(): Parent {
            return Parent()
        }

        @Bean
        fun child(): Child {
            return Child()
        }

        @Bean
        fun atTargetAtWithinAspect(): AtTargetAtWithinAspect {
            return AtTargetAtWithinAspect()
        }
    }

    open class Parent {
        fun parentMethod() {} // 부모에만 있는 메서드
    }

    @ClassAop
    class Child : Parent() {
        fun childMethod() {} // 자식에만 있는 메서드
    }

    @Aspect
    class AtTargetAtWithinAspect {
        private val logger = mu.KotlinLogging.logger {}

        // @target: 인스턴스 기준으로 모든 메서드의 조인 포인트를 선정, 부모 타입의 메서드도 적용
        @Around(
            "execution(* com.example.spring.advanced.aop..*(..)) && " +
                    "@target(com.example.spring.advanced.aop.member.annotation.ClassAop)"
        )
        fun atTarget(pointcut: org.aspectj.lang.ProceedingJoinPoint): Any {
            logger.info("[@target] {}", pointcut.signature)
            return pointcut.proceed()
        }

        // @within: 선택된 클래스 내부에 있는 메서드만 조인 포인트로 선정, 부모 타입의 메서드는 적용되지 않음
        @Around(
            "execution(* com.example.spring.advanced.aop..*(..)) && " +
                    "@within(com.example.spring.advanced.aop.member.annotation.ClassAop)"
        )
        fun atWithin(pointcut: org.aspectj.lang.ProceedingJoinPoint): Any {
            logger.info("[@within] {}", pointcut.signature)
            return pointcut.proceed()
        }
    }
}

