package com.example.learning

import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class CoreApplication {
}

fun main(args: Array<String>) {
    org.springframework.boot.runApplication<CoreApplication>(*args)
}