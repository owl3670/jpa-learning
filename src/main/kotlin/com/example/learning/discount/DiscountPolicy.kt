package com.example.learning.discount

import com.example.learning.member.Member

interface DiscountPolicy {
    fun discount(member: Member, price: Int): Int
}