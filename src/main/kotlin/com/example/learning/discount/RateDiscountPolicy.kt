package com.example.learning.discount

import com.example.learning.member.Grade
import com.example.learning.member.Member

class RateDiscountPolicy : DiscountPolicy {
    val discountPercent = 10

    override fun discount(member: Member, price: Int): Int {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100
        }else{
            return 0
        }
    }
}