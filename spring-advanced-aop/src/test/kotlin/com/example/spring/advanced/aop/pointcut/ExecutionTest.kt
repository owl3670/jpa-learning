package com.example.spring.advanced.aop.pointcut

import com.example.spring.advanced.aop.member.MemberServiceImpl
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
}