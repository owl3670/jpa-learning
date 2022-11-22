package com.spring.advanced.proxy

import com.spring.advanced.proxy.code.CacheProxy
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

    @Test
    fun cacheProxyTest(){
        val realSubject = RealSubject()
        val cacheProxy = CacheProxy(realSubject)
        val proxyPatternClient = ProxyPatternClient(cacheProxy)
        proxyPatternClient.execute()
        proxyPatternClient.execute()
        proxyPatternClient.execute()
    }
}