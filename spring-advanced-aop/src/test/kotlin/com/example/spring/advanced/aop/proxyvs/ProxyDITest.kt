package com.example.spring.advanced.aop.proxyvs

import com.example.spring.advanced.aop.member.MemberService
import com.example.spring.advanced.aop.member.MemberServiceImpl
import com.example.spring.advanced.aop.proxyvs.code.ProxyDIAspect
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest(properties = ["spring.aop.proxy-target-class=false"])
@Import(ProxyDIAspect::class)
class ProxyDITest {
    private val logger = mu.KotlinLogging.logger {}

    @Autowired private lateinit var memberService: MemberService
    @Autowired private lateinit var mamberServiceImpl: MemberServiceImpl

    @Test
    fun go(){
        logger.info("memberService class={}", memberService.javaClass)
        logger.info("mamberServiceImpl class={}", mamberServiceImpl.javaClass)
        memberService.hello("hello")
    }
}