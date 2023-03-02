package com.example.spring.advanced.aop.internalcall

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(CallLogAspect::class)
@SpringBootTest
class CallServiceV1Test {
    @Autowired
    private lateinit var callServiceV1: CallServiceV1

    @Test
    fun external() {
        callServiceV1.external()
    }
}