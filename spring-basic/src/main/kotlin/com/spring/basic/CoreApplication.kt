package com.spring.basic

import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class CoreApplication {
}

fun main(args: Array<String>) {
    org.springframework.boot.runApplication<CoreApplication>(*args)
}