package com.example.learning.scope

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

class SingletonWithPrototypeTest1 {
    @Test
    fun prototypeFind() {
        val ac = AnnotationConfigApplicationContext(ClientBeanTest1::class.java, PrototypeBeanTest1::class.java)
        val bean1 = ac.getBean(ClientBeanTest1::class.java)
        val count1 = bean1.logic()
        assertThat(count1).isEqualTo(1)

        val bean2 = ac.getBean(ClientBeanTest1::class.java)
        val count2 = bean2.logic()
        assertThat(count2).isEqualTo(2)
    }
}

class ClientBeanTest1(private val prototypeBeanTest1: PrototypeBeanTest1) {
    fun logic(): Int {
        this.prototypeBeanTest1.addCount()
        return prototypeBeanTest1.getCount()
    }
}

@Scope("prototype")
class PrototypeBeanTest1 {
    private var count = 0

    fun addCount() {
        count++
    }

    fun getCount(): Int {
        return count
    }

    @PostConstruct
    fun init() {
        println("PrototypeBean.init")
    }

    @PreDestroy
    fun destroy() {
        println("PrototypeBean.destroy")
    }
}