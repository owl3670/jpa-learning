package com.spring.basic.web

import com.spring.basic.common.MyLogger
import com.spring.basic.logdemo.LogDemoService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest

@Controller
class LogDemoController (
    private val logDemoService: LogDemoService,
    private val myLogger: MyLogger
        ){
    @RequestMapping("log-demo")
    @ResponseBody
    fun logDemo(req: HttpServletRequest): String {
        val requestURL = req.requestURL.toString()
        myLogger.setRequestUrl(requestURL)
        myLogger.writeLog("controller test")
        logDemoService.logic("testId")
        return "OK"
    }
}