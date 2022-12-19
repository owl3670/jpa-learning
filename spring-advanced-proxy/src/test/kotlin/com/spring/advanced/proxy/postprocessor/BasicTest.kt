package com.spring.advanced.proxy.postprocessor

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BasicTest {
    @Test
    fun basicConfig(){
        val applicationContext = AnnotationConfigApplicationContext(BasicConfig::class.java)
        val a : A = applicationContext.getBean("beanA", A::class.java)
        a.helloA()

        Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) {
            applicationContext.getBean("beanB", B::class.java)
        }
    }

    @Configuration
    class BasicConfig {
        @Bean(name = ["beanA"])
        fun a(): A {
            return A()
        }
    }

    class A {
        private val logger = mu.KotlinLogging.logger {}

        fun helloA(){
            logger.info("helloA")
        }
    }

    class B {
        private val logger = mu.KotlinLogging.logger {}

        fun helloB(){
            logger.info("helloB")
        }
    }
}