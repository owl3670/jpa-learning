package com.example.learning.singleton

import com.example.learning.AppConfig
import com.example.learning.member.MemberRepository
import com.example.learning.member.MemberServiceImpl
import com.example.learning.order.OrderServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ConfigurationSingletonTest {
    @Test
    fun configurationTest() {
        val ac = AnnotationConfigApplicationContext(AppConfig::class.java)
        val memberService = ac.getBean("memberService", MemberServiceImpl::class.java)
        val orderService = ac.getBean("orderService", OrderServiceImpl::class.java)
        val memberRepository = ac.getBean("memberRepository", MemberRepository::class.java)

        // 모두 같은 인스턴스를 참고하고 있다.
        println("memberService.memberRepository = ${memberService.getMemberRepository()}")
        println("orderService.memberRepository = ${orderService.getMemberRepository()}")
        println("memberRepository = ${memberRepository}")

        // 모두 같은 인스턴스를 참고하고 있다.
        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository)
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository)
    }
}