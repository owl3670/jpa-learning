package com.example.learning.singleton

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean

class StatefulServiceTest {
    @Test
    fun statefulServiceSingleton() {
        val ac = AnnotationConfigApplicationContext(TestConfig::class.java)

        val statefulService1 = ac.getBean("statefulService", StatefulService::class.java)
        val statefulService2 = ac.getBean("statefulService", StatefulService::class.java)

        // Thread A: A 사용자 10000원 주문
        statefulService1.order("userA", 10000)
        // Thread B: B 사용자 20000원 주문
        statefulService2.order("userB", 20000)

        // Thread A: 사용자 A 주문 금액 조회
        val price = statefulService1.getPrice()

        // Thread A: A 사용자는 10000원을 기대했지만, 20000원 출력
        println("price = $price")

        assertThat(statefulService1.getPrice()).isEqualTo(20000)
    }

    class TestConfig{
        @Bean
        fun statefulService() = StatefulService()
    }
}

