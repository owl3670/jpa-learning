package com.example.spring.advanced.aop.proxyvs

import com.example.spring.advanced.aop.member.MemberService
import com.example.spring.advanced.aop.member.MemberServiceImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.aop.framework.ProxyFactory

class ProxyCasingTest {
    private val logger = mu.KotlinLogging.logger {}

    @Test
    fun jdkProxy() {
        val target = MemberServiceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.isProxyTargetClass = false

        val memberServiceProxy = proxyFactory.proxy as MemberService

        logger.info("proxy class={}", memberServiceProxy::class.java)

        assertThrows<java.lang.ClassCastException> {
            val castingMemberService = memberServiceProxy as MemberServiceImpl
        }
    }

    @Test
    fun cglibProxy(){
        val target = MemberServiceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.isProxyTargetClass = true

        val memberServiceProxy = proxyFactory.proxy as MemberService

        logger.info("proxy class={}", memberServiceProxy::class.java)

        val castingMemberService = memberServiceProxy as MemberServiceImpl
    }
}