package com.spring.advanced.proxy

import com.spring.advanced.proxy.config.v1_proxy.InterfaceProxyConfig
import com.spring.advanced.proxy.trace.logtrace.LogTrace
import com.spring.advanced.proxy.trace.logtrace.ThreadLocalLogTrace
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

//@Import(AppV1Config::class, AppV2Config::class)
@Import(InterfaceProxyConfig::class)
@SpringBootApplication(scanBasePackages = ["com.spring.advanced.proxy.app"])
class SpringAdvancedProxyApplication{
    @Bean
    fun logTrace(): LogTrace {
        return ThreadLocalLogTrace()
    }
}

fun main(args: Array<String>) {
    runApplication<SpringAdvancedProxyApplication>(*args)
}
