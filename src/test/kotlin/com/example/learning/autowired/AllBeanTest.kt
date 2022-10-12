package com.example.learning.autowired

import com.example.learning.AutoAppConfig
import com.example.learning.discount.DiscountPolicy
import com.example.learning.member.Grade
import com.example.learning.member.Member
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AllBeanTest {
    @Test
    fun findAllBean() {
        val ac = AnnotationConfigApplicationContext(AutoAppConfig::class.java, DiscountService::class.java)
        val discountService: DiscountService = ac.getBean(DiscountService::class.java)
        val member = Member(1L, "userA", Grade.VIP)
        val discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy")

        assertThat(discountService).isInstanceOf(DiscountService::class.java)
        assertThat(discountPrice).isEqualTo(1000)
    }
}

class DiscountService(
    private val policyMap: Map<String, DiscountPolicy>,
    discountPolicies: List<DiscountPolicy>,
) {
    init{
        println("poicyMap = $policyMap")
        println("discountPolicies = $discountPolicies")
    }

    fun discount(member: Member, price: Int, discountCode: String): Int {
        val discountPolicy = policyMap[discountCode]
        return discountPolicy?.discount(member, price) ?: 0
    }
}