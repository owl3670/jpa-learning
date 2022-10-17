package com.example.learning.scope

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

class PrototypeTest {
    @Test
    fun prototypeBeanFind(){
        val ac = AnnotationConfigApplicationContext(PrototypeBean::class.java)
        val bean1 = ac.getBean(PrototypeBean::class.java)
        val bean2 = ac.getBean(PrototypeBean::class.java)
        println("prototypeBean1 = $bean1")
        println("prototypeBean2 = $bean2")
        assertThat(bean1).isNotSameAs(bean2)
        ac.close()
    }
}

@Scope("prototype")
class PrototypeBean {
    @PostConstruct
    fun init(){
        println("PrototypeBean.init")
    }

    @PreDestroy
    fun destroy(){
        println("PrototypeBean.destroy")
    }
}
