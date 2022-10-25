package com.spring.basic.scope

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

class SingletonTest {
    @Test
    fun singletonBeanFind(){
        val ac = AnnotationConfigApplicationContext(SingletonBean::class.java)
        val bean1 = ac.getBean(SingletonBean::class.java)
        val bean2 = ac.getBean(SingletonBean::class.java)
        println("singletonBean1 = $bean1")
        println("singletonBean2 = $bean2")
        assertThat(bean1).isSameAs(bean2)
        ac.close()
    }

}

@Scope("singleton")
class SingletonBean {
    @PostConstruct
    fun init(){
        println("SingletonBean.init")
    }

    @PreDestroy
    fun destroy(){
        println("SingletonBean.destroy")
    }
}
