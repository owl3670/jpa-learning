package com.example.learning.discount

import com.example.learning.member.Grade
import com.example.learning.member.Member
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class RateDiscountPolicyTest{
    val discountPolicy: DiscountPolicy = RateDiscountPolicy()

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    fun vip_o(){
        // given
        val member = Member(1L, "memberVIP", Grade.VIP)

        // when
        val discountPrice = discountPolicy.discount(member, 10000)

        // then
        assertEquals(discountPrice, 1000)
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    fun vip_x(){
        // given
        val member = Member(2L, "memberBASIC", Grade.BASIC)

        // when
        val discountPrice = discountPolicy.discount(member, 10000)

        // then
        assertEquals(discountPrice, 0)
    }
}