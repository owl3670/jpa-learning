package com.spring.advanced.proxy.code

class ProxyPatternClient(
    private val subject: Subject
) {
    fun execute(){
        subject.operation()
    }
}