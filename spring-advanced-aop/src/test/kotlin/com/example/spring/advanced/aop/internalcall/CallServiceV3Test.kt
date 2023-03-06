package com.example.spring.advanced.aop.internalcall

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(CallLogAspect::class)
@SpringBootTest
class CallServiceV3Test {
    @Autowired
    private lateinit var callServiceV3: CallServiceV3

    @Test
    fun external() {
        callServiceV3.external()
    }
}