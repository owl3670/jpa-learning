package com.example.spring.advanced.aop.exam

import com.example.spring.advanced.aop.exam.annotation.Trace
import org.springframework.stereotype.Repository

@Repository
class ExamRepository {
    companion object{
        private var seq: Int = 0
    }

    @Trace
    fun save(itemId: String): String {
        seq++
        if (seq % 5 == 0){
            throw IllegalStateException("예외 발생")
        }
        return "ok"
    }
}