package com.spring.basic.order

import com.spring.basic.annotation.MainDiscountPolicy
import com.spring.basic.discount.DiscountPolicy
import com.spring.basic.member.MemberRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class OrderServiceImpl(
    private val memberRepository: MemberRepository,
    @MainDiscountPolicy private val discountPolicy: DiscountPolicy
) : OrderService {

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