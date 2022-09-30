package com.example.learning

import com.example.learning.member.Grade
import com.example.learning.member.Member
import com.example.learning.member.MemberService
import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main(args: Array<String>){
    val applicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
    val memberService = applicationContext.getBean("memberService", MemberService::class.java)

    val member = Member(1L, "memberA", Grade.VIP)
    memberService.join(member)

    val findMember = memberService.findMember(1L)
    println("new member = ${member.getName()}")
    println("find member = ${findMember?.getName()}")

}