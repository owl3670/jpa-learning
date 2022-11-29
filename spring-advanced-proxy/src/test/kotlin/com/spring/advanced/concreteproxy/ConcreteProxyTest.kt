package com.spring.advanced.concreteproxy

import com.spring.advanced.concreteproxy.code.ConcreteClient
import com.spring.advanced.concreteproxy.code.ConcreteLogic
import com.spring.advanced.concreteproxy.code.TimeProxy
import org.junit.jupiter.api.Test

class ConcreteProxyTest {
    @Test
    fun noProxy(){
        val concreteLogic = ConcreteLogic()
        val concreteClient = ConcreteClient(concreteLogic)
        concreteClient.execute()
    }

    @Test
    fun addProxy(){
        val concreteLogic = ConcreteLogic()
        val timeProxy = TimeProxy(concreteLogic)
        val concreteClient = ConcreteClient(timeProxy)
        concreteClient.execute()
    }
}