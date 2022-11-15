package com.spring.advanced.proxy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@Import(AppV1Config::class)
@SpringBootApplication(scanBasePackages = ["com.spring.advanced.proxy.app"])
class SpringAdvancedProxyApplication

fun main(args: Array<String>) {
    runApplication<SpringAdvancedProxyApplication>(*args)
}
