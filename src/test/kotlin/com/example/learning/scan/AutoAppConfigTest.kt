package com.example.learning.scan

import com.example.learning.AutoAppConfig
import com.example.learning.member.MemberService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AutoAppConfigTest {
    @Test
    fun basicScan() {
        val ac = AnnotationConfigApplicationContext(AutoAppConfig::class.java)
        val bean = ac.getBean(MemberService::class.java)
        assertThat(bean).isInstanceOf(MemberService::class.java)
    }
}