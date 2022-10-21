package com.example.learning.common

import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
class MyLogger {
    private var uuid: String? = null
    private var requestURL: String? = null

    fun setRequestUrl(url: String?) {
        this.requestURL = url
    }

    fun setUUID(uuid: String?) {
        this.uuid = uuid
    }

    fun writeLog(logMessage: String?) {
        println("[$uuid][$requestURL]$logMessage")
    }

    @PostConstruct
    fun init() {
        this.uuid = UUID.randomUUID().toString()
        println("Request Scope Bean Initialized - [$uuid] : $this")
    }

    @PreDestroy
    fun destroy() {
        println("Request Scope Bean Destroyed - [$uuid] : $this")
    }
}