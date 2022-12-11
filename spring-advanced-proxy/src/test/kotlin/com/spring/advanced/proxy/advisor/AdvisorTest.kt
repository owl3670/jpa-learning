package com.spring.advanced.proxy.advisor

import com.spring.advanced.proxy.common.advice.TimeAdvice
import com.spring.advanced.proxy.common.service.ServiceImpl
import com.spring.advanced.proxy.common.service.ServiceInterface
import org.junit.jupiter.api.Test
import org.springframework.aop.Pointcut
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor

class AdvisorTest {
    @Test
    fun advisorTest1(){
        val target: ServiceInterface = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        val advisor = DefaultPointcutAdvisor(Pointcut.TRUE, TimeAdvice())
        proxyFactory.addAdvisor(advisor)
        val proxy = proxyFactory.proxy as ServiceInterface

        proxy.save()
        proxy.find()
    }
}