package com.example.spring.advanced.aop.exam

import com.example.spring.advanced.aop.exam.aop.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

//@Import(TraceAspect::class)
@Import(value=[TraceAspect::class, RetryAspect::class])
@SpringBootTest
class ExamTest {
    @Autowired private lateinit var examService: ExamService

    @Test
    fun test() {
        for (i in 1..5) {
            examService.request("data$i")
        }
    }
}