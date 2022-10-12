package com.example.learning.discount

import com.example.learning.member.Grade
import com.example.learning.member.Member
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
@Qualifier("fixDiscountPolicy")
class FixDiscountPolicy : DiscountPolicy{

   private val discountFixAmount = 1000

    override fun discount(member: Member, price: Int): Int {
        if (member.getGrade() == Grade.VIP){
           return discountFixAmount
        }else{
            return 0
        }
    }
}