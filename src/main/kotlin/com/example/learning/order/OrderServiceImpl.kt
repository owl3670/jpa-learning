package com.example.learning.order

import com.example.learning.discount.DiscountPolicy
import com.example.learning.member.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OrderServiceImpl : OrderService {
    private lateinit var memberRepository: MemberRepository
    private lateinit var discountPolicy: DiscountPolicy

    @Autowired
    fun init(memberRepository: MemberRepository, discountPolicy: DiscountPolicy) {
        this.memberRepository = memberRepository
        this.discountPolicy = discountPolicy
    }

    override fun createOrder(memberId: Long, itemName: String, itemPrice: Int): Order {
        val member = memberRepository.findById(memberId)
        val discountPrice = discountPolicy.discount(member!!, itemPrice)

        return Order(memberId, itemName, itemPrice, discountPrice)
    }

    // 테스트 용도
    fun getMemberRepository(): MemberRepository {
        return memberRepository
    }
}