package com.example.learning.scope

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

class PrototypeProviderTest {
    @Test
    fun providerTest() {
        val ac = AnnotationConfigApplicationContext(ClientBeanTest2::class.java, PrototypeBeanTest2::class.java)
        val bean1 = ac.getBean(ClientBeanTest2::class.java)
        val count1 = bean1.logic()
        assertThat(count1).isEqualTo(1)

        val bean2 = ac.getBean(ClientBeanTest2::class.java)
        val count2 = bean2.logic()
        assertThat(count2).isEqualTo(1)
    }
}

class ClientBeanTest2(@Autowired private val ac: ApplicationContext) {
    fun logic(): Int {
        val prototypeBean = this.ac.getBean(PrototypeBeanTest2::class.java)
        prototypeBean.addCount()
        return prototypeBean.getCount()
    }
}

@Scope("prototype")
class PrototypeBeanTest2 {
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
