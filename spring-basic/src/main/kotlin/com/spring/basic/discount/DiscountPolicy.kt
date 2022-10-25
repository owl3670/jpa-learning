package com.spring.basic.discount

import com.spring.basic.member.Member

interface DiscountPolicy {
    fun discount(member: Member, price: Int): Int
}