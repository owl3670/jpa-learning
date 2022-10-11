package com.example.learning

import com.example.learning.discount.DiscountPolicy
import com.example.learning.discount.FixDiscountPolicy
import com.example.learning.discount.RateDiscountPolicy
import com.example.learning.member.MemberService
import com.example.learning.member.MemberServiceImpl
import com.example.learning.member.MemoryMemberRepository
import com.example.learning.order.OrderService
import com.example.learning.order.OrderServiceImpl
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