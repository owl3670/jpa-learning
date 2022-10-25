package com.spring.basic

import com.spring.basic.member.Grade
import com.spring.basic.member.Member
import com.spring.basic.member.MemberService
import com.spring.basic.order.OrderService
import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main(args: Array<String>){
    val applicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

    val memberService = applicationContext.getBean("memberService", MemberService::class.java)
    val orderService = applicationContext.getBean("orderService", OrderService::class.java)

    val member = Member(1L, "memberA", Grade.VIP)
    memberService.join(member)

    val order = orderService.createOrder(1L, "itemA", 10000)
    println("order = ${order}")
}