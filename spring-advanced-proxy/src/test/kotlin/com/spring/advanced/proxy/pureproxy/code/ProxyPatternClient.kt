package com.spring.advanced.proxy.pureproxy.code

class ProxyPatternClient(
    private val subject: Subject
) {
    fun execute(){
        subject.operation()
    }
}