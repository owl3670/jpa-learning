package com.example.learning.order

import com.example.learning.AppConfig
import com.example.learning.member.Grade
import com.example.learning.member.Member
import com.example.learning.member.MemberService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class OrderServiceImplTest {
    lateinit var memberService: MemberService
    lateinit var orderService: OrderService

    @BeforeEach
    fun beforeEach(){
        val appConfig = AppConfig()
        memberService = appConfig.memberService()
        orderService = appConfig.orderService()
    }

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