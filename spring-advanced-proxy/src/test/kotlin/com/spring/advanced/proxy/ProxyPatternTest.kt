package com.spring.advanced.proxy

import com.spring.advanced.proxy.code.ProxyPatternClient
import com.spring.advanced.proxy.code.RealSubject
import org.junit.jupiter.api.Test

class ProxyPatternTest {
    @Test
    fun noProxyTest(){
        val realSubject = RealSubject()
        val proxyPatternClient = ProxyPatternClient(realSubject)
        proxyPatternClient.execute()
        proxyPatternClient.execute()
        proxyPatternClient.execute()
    }
}