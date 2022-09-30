package com.example.learning

import com.example.learning.member.Grade
import com.example.learning.member.Member
import com.example.learning.member.MemberService
import com.example.learning.order.OrderService
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