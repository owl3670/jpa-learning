package com.example.learning.singleton

import com.example.learning.AppConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    fun pureContainer(){
        val appConfig = AppConfig()
        // 1. 조회: 호출할 때 마다 객체를 생성
        val memberService1 = appConfig.memberService()

        // 2. 조회: 호출할 때 마다 객체를 생성
        val memberService2 = appConfig.memberService()

        // 참조값이 다른 것을 확인
        println("memberService1 = ${memberService1}")
        println("memberService2 = ${memberService2}")

        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2)
    }
}