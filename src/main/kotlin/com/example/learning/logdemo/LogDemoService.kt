package com.example.learning.logdemo

import com.example.learning.common.MyLogger
import org.springframework.stereotype.Service

@Service
class LogDemoService(
    private val myLogger: MyLogger
) {
    fun logic(id: String) {
        myLogger.writeLog("service id = $id")
    }
}
