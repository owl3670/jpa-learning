package com.example.learning.order

import com.example.learning.discount.DiscountPolicy
import com.example.learning.discount.FixDiscountPolicy
import com.example.learning.member.Grade
import com.example.learning.member.Member
import com.example.learning.member.MemberService
import com.example.learning.member.MemberServiceImpl
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class OrderServiceImplTest {
    val memberService: MemberService = MemberServiceImpl()
    val orderService: OrderService = OrderServiceImpl()

    @Test
    fun createOrder(){
        // given
        val memberId = 1L
        val member = Member(memberId, "memberA", Grade.VIP)
        memberService.join(member)

        // when
        val order = orderService.createOrder(memberId, "itemA", 10000)

        // then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000)
    }
}