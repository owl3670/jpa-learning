package com.spring.advanced.proxy.concreteproxy.code

class ConcreteClient(
    private val concreteLogic: ConcreteLogic
) {
    fun execute() {
        concreteLogic.operation()
    }
}