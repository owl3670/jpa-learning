package com.spring.basic.member

import com.spring.basic.AppConfig
import com.spring.basic.member.Grade
import com.spring.basic.member.Member
import com.spring.basic.member.MemberService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MemberServiceTest {
    lateinit var memberService: MemberService

    @BeforeEach
    fun beforeEach(){
        val appConfig = AppConfig()
        memberService = appConfig.memberService()
    }

    @Test
    fun join() {
        // given
        val member = Member(1L, "memberA", Grade.VIP)

        // when
        memberService.join(member)
        val findMember = memberService.findMember(1L)

        // then
        Assertions.assertThat(member).isEqualTo(findMember)
    }
}