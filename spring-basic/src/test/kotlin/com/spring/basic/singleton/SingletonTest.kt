package com.spring.basic.singleton

import com.spring.basic.AppConfig
import com.spring.basic.member.MemberService
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

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

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    fun singletonServiceTest() {

        // private으로 생성자를 막아놨기 때문에 new 키워드를 사용할 수 없다.
        // val singletonService = SingletonService()

        // 1. 조회: 호출할 때 마다 같은 객체를 반환
        val singletonService1 = SingletonService.getInstance()
        // 2. 조회: 호출할 때 마다 같은 객체를 반환
        val singletonService2 = SingletonService.getInstance()

        // 참조값이 같은 것을 확인
        println("singletonService1 = ${singletonService1}")
        println("singletonService2 = ${singletonService2}")

        // singletonService1 == singletonService2
        Assertions.assertThat(singletonService1).isSameAs(singletonService2)

        singletonService1.logic()
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    fun springContainer(){
        val ac = AnnotationConfigApplicationContext(AppConfig::class.java)

        // 1. 조회: 호출할 때 마다 같은 객체를 반환
        val memberService1 = ac.getBean("memberService", MemberService::class.java)

        // 2. 조회: 호출할 때 마다 같은 객체를 반환
        val memberService2 = ac.getBean("memberService", MemberService::class.java)

        // 참조값이 같은 것을 확인
        println("memberService1 = ${memberService1}")
        println("memberService2 = ${memberService2}")

        // memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2)
    }
}