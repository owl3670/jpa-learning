package com.example.learning

import com.example.learning.discount.DiscountPolicy
import com.example.learning.discount.FixDiscountPolicy
import com.example.learning.discount.RateDiscountPolicy
import com.example.learning.member.MemberService
import com.example.learning.member.MemberServiceImpl
import com.example.learning.member.MemoryMemberRepository
import com.example.learning.order.OrderService
import com.example.learning.order.OrderServiceImpl

class AppConfig {
    fun memberService(): MemberService {
        return MemberServiceImpl(memberRepository())
    }

    fun orderService(): OrderService {
        return OrderServiceImpl(memberRepository(), discountPolicy())
    }

    fun memberRepository(): MemoryMemberRepository {
        return MemoryMemberRepository()
    }

    fun discountPolicy(): DiscountPolicy {
        return RateDiscountPolicy()
    }
}