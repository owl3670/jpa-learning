package com.example.learning.logdemo

import com.example.learning.common.MyLogger
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Service

@Service
class LogDemoService(
    private val myLoggerProvider: ObjectProvider<MyLogger>
) {
    fun logic(id: String) {
        val myLogger = myLoggerProvider.getObject()
        myLogger.writeLog("service id = $id")
    }
}
