package com.spring.basic.beanfind

import com.spring.basic.discount.DiscountPolicy
import com.spring.basic.discount.FixDiscountPolicy
import com.spring.basic.discount.RateDiscountPolicy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class ApplicationContextExtendsFindTest {
    val ac = AnnotationConfigApplicationContext(TestConfig::class.java)

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다.")
    fun findBeanByParentTypeDuplicate() {
        assertThrows<NoUniqueBeanDefinitionException> {
            ac.getBean(DiscountPolicy::class.java)
        }
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    fun findBeanByParentTypeBeanName() {
        val bean = ac.getBean("rateDiscountPolicy", DiscountPolicy::class.java)
        assertThat(bean).isInstanceOf(RateDiscountPolicy::class.java)
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    fun findBeanBySubType() {
        val bean = ac.getBean(FixDiscountPolicy::class.java)
        assertThat(bean).isInstanceOf(FixDiscountPolicy::class.java)
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    fun findAllBeanByParentType() {
        val beans = ac.getBeansOfType(DiscountPolicy::class.java)
        assertThat(beans.size).isEqualTo(2)
        for (key in beans.keys) {
            println("key = $key value = ${beans[key]}")
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    fun findAllBeanByObjectType() {
        val beans = ac.getBeansOfType(Object::class.java)
        for (key in beans.keys) {
            println("key = $key value = ${beans[key]}")
        }
    }

    @Configuration
    class TestConfig {
        @Bean
        fun rateDiscountPolicy() = RateDiscountPolicy()

        @Bean
        fun fixDiscountPolicy() = FixDiscountPolicy()
    }
}