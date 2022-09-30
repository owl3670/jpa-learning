package com.example.learning

import com.example.learning.discount.DiscountPolicy
import com.example.learning.discount.FixDiscountPolicy
import com.example.learning.member.MemberService
import com.example.learning.member.MemberServiceImpl
import com.example.learning.member.MemoryMemberRepository
import com.example.learning.order.OrderService
import com.example.learning.order.OrderServiceImpl

class AppConfig {
    fun memberService(): MemberService {
        return MemberServiceImpl(MemoryMemberRepository())
    }

    fun orderService(): OrderService {
        return OrderServiceImpl(MemoryMemberRepository(), discountPolicy())
    }

    fun discountPolicy(): DiscountPolicy {
        return FixDiscountPolicy()
    }
}