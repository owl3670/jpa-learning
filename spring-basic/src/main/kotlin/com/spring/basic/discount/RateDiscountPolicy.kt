package com.spring.basic.discount

import com.spring.basic.annotation.MainDiscountPolicy
import com.spring.basic.member.Grade
import com.spring.basic.member.Member
import org.springframework.stereotype.Component

@Component
@MainDiscountPolicy
class RateDiscountPolicy : DiscountPolicy {
    val discountPercent = 10

    override fun discount(member: Member, price: Int): Int {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100
        } else {
            return 0
        }
    }
}