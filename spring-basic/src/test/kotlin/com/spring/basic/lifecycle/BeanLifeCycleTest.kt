package com.spring.basic.lifecycle

import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BeanLifeCycleTest {
    @Test
    fun lifecycleTest() {
        val ac = AnnotationConfigApplicationContext(LifeCycleConfig::class.java)
        val networkClient = ac.getBean(NetworkClient::class.java)
        ac.close()
    }
}

@Configuration
class LifeCycleConfig {
    @Bean
    fun networkClient(): NetworkClient {
        val networkClient = NetworkClient()
        networkClient.setUrl("http://hello-spring.dev")
        return networkClient
    }
}