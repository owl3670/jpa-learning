package com.spring.basic.order

import com.spring.basic.AppConfig
import com.spring.basic.member.Grade
import com.spring.basic.member.Member
import com.spring.basic.member.MemberService
import com.spring.basic.order.OrderService
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