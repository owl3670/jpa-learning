package com.spring.basic

import com.spring.basic.discount.DiscountPolicy
import com.spring.basic.discount.RateDiscountPolicy
import com.spring.basic.member.MemberService
import com.spring.basic.member.MemberServiceImpl
import com.spring.basic.member.MemoryMemberRepository
import com.spring.basic.order.OrderService
import com.spring.basic.order.OrderServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun memberService(): MemberService {
        // 1번
        println("call AppConfig.memberService")
        return MemberServiceImpl(memberRepository())
    }

    @Bean
    fun orderService(): OrderService {
        // 1번
        println("call AppConfig.orderService")
        return OrderServiceImpl(memberRepository(), discountPolicy())
    }

    @Bean
    fun memberRepository(): MemoryMemberRepository {
        // 2번, 3번??
        println("call AppConfig.memberRepository")
        return MemoryMemberRepository()
    }

    @Bean
    fun discountPolicy(): DiscountPolicy {
        return RateDiscountPolicy()
    }
}