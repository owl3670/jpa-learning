package com.example.learning.scan.filter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

class ComponentFilterAppConfigTest {
    @Test
    fun filterScan() {
        val ac = AnnotationConfigApplicationContext(ComponentFilterAppConfig::class.java)

        val beanA = ac.getBean("beanA", BeanA::class.java)
        assertThat(beanA).isNotNull

        Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) {
            ac.getBean("beanB", BeanB::class.java)
        }
    }

    @Configuration
    @ComponentScan(
        includeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION, classes = [MyIncludeComponent::class])],
        excludeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION, classes = [MyExcludeComponent::class])]
    )
    class ComponentFilterAppConfig {
    }
}