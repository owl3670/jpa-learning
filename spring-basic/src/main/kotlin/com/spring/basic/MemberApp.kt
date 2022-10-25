package com.spring.basic

import com.spring.basic.member.Grade
import com.spring.basic.member.Member
import com.spring.basic.member.MemberService
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