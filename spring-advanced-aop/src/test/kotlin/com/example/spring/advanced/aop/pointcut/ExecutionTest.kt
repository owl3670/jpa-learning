package com.example.spring.advanced.aop.pointcut

import com.example.spring.advanced.aop.member.MemberServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import java.lang.reflect.Method

class ExecutionTest {
    private val logger = mu.KotlinLogging.logger {}

    val pointcut: AspectJExpressionPointcut = AspectJExpressionPointcut()
    lateinit var helloMethod: Method

    @BeforeEach
    fun init(){
        helloMethod = MemberServiceImpl::class.java.getMethod("hello", String::class.java)
    }

    @Test
    fun printMethod(){
        logger.info("helloMethod={}", helloMethod)
    }

    @Test
    fun exactMatch(){
        pointcut.expression = "execution(public String com.example.spring.advanced.aop.member.MemberService.hello(java.lang.String))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun allMatch(){
        pointcut.expression = "execution(* *(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun nameMatch(){
        pointcut.expression = "execution(* hello(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun nameMatchStar1(){
        pointcut.expression = "execution(* hel*(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun nameMatchStar2(){
        pointcut.expression = "execution(* *el*(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun nameMatchFalse(){
        pointcut.expression = "execution(* nono(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isFalse
    }

    @Test
    fun packageExactMatch1(){
        pointcut.expression = "execution(* com.example.spring.advanced.aop.member.MemberServiceImpl.hello(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun packageExactMatch2(){
        pointcut.expression = "execution(* com.example.spring.advanced.aop.member.*.*(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun packageExactMatchFalse(){
        pointcut.expression = "execution(* com.example.spring.advanced.aop.*.*(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isFalse
    }

    @Test
    fun packageMatchSubPackage1(){
        pointcut.expression = "execution(* com.example.spring.advanced.aop.member..*.*(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun packageMatchSubPackage2(){
        pointcut.expression = "execution(* com.example.spring.advanced.aop..*.*(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }
}