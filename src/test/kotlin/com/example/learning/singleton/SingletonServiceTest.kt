package com.example.learning.singleton

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SingletonServiceTest {
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
}