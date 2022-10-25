package com.spring.basic.logdemo

import com.spring.basic.common.MyLogger
import org.springframework.stereotype.Service

@Service
class LogDemoService(
    private val myLogger: MyLogger
) {
    fun logic(id: String) {
        myLogger.writeLog("service id = $id")
    }
}
