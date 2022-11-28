package com.spring.advanced.concreteproxy

import com.spring.advanced.concreteproxy.code.ConcreteClient
import com.spring.advanced.concreteproxy.code.ConcreteLogic
import org.junit.jupiter.api.Test

class ConcreteProxyTest {
    @Test
    fun noProxy(){
        val concreteLogic = ConcreteLogic()
        val concreteClient = ConcreteClient(concreteLogic)
        concreteClient.execute()
    }
}