package com.spring.advanced.proxy.postprocessor

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BeanPostProcessorTest {
    @Test
    fun postProcessor(){
        val applicationContext = AnnotationConfigApplicationContext(BeanPostProcessorConfig::class.java)

        val b = applicationContext.getBean("beanA", B::class.java)
        b.helloB()

        Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) {
            applicationContext.getBean(A::class.java)
        }
    }

    @Configuration
    class BeanPostProcessorConfig{
        @Bean(name=["beanA"])
        fun a(): A {
            return A()
        }

        @Bean
        fun helloPostProcessor(): AToBPostProcessor{
            return AToBPostProcessor()
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

    class AToBPostProcessor: BeanPostProcessor {
        private val logger = mu.KotlinLogging.logger {}

        override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
            logger.info("beanName=$beanName bean=$bean")
            if(bean is A){
                return B()
            }
            return bean
        }
    }
}