package com.example.learning.order

import com.example.learning.discount.DiscountPolicy
import com.example.learning.discount.FixDiscountPolicy
import com.example.learning.member.MemberRepository
import com.example.learning.member.MemoryMemberRepository

class OrderServiceImpl : OrderService {
    private val memberRepository: MemberRepository = MemoryMemberRepository()
    private val discountPolicy: DiscountPolicy = FixDiscountPolicy()

    override fun createOrder(memberId: Long, itemName: String, itemPrice: Int): Order {
        val member = memberRepository.findById(memberId)
        val discountPrice = discountPolicy.discount(member!!, itemPrice)

        return Order(memberId, itemName, itemPrice, discountPrice)
    }
}