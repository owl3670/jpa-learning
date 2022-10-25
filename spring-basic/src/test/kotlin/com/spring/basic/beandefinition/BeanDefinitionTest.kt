package com.spring.basic.beandefinition

import com.spring.basic.AppConfig
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class BeanDefinitionTest {
    val ac = AnnotationConfigApplicationContext(AppConfig::class.java)

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    fun findApplicationBean() {
        val beanDefinitionNames = ac.beanDefinitionNames
        for (beanDefinitionName in beanDefinitionNames) {
            val beanDefinition = ac.getBeanDefinition(beanDefinitionName)
            if (beanDefinition.role == BeanDefinition.ROLE_APPLICATION) {
                println("beanDefinitionName = $beanDefinitionName beanDefinition = $beanDefinition")
            }
        }
    }
}