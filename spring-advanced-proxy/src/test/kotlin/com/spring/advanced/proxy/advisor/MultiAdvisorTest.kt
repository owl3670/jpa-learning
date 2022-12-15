package com.spring.advanced.proxy.advisor

import com.spring.advanced.proxy.common.service.ServiceImpl
import com.spring.advanced.proxy.common.service.ServiceInterface
import mu.KotlinLogging
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.aop.Pointcut
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor

class MultiAdvisorTest {
    @Test
    @DisplayName("여러 프록시")
    fun multiAdvisorTest1(){
        val target: ServiceInterface = ServiceImpl()
        val proxyFactory1 = ProxyFactory(target)
        val advisor1 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice1())
        proxyFactory1.addAdvisor(advisor1)
        val proxy1 = proxyFactory1.proxy as ServiceInterface

        val proxyFactory2 = ProxyFactory(proxy1)
        val advisor2 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice2())
        proxyFactory2.addAdvisor(advisor2)
        val proxy2 = proxyFactory2.proxy as ServiceInterface

        proxy2.save()
    }

    class Advice1 : MethodInterceptor {
        private val logger = mu.KotlinLogging.logger {}

        override fun invoke(invocation: MethodInvocation): Any? {
            logger.info("advice1 호출")
            return invocation.proceed()
        }
    }

    class Advice2 : MethodInterceptor {
        private val logger = mu.KotlinLogging.logger {}

        override fun invoke(invocation: MethodInvocation): Any? {
            logger.info("advice2 호출")
            return invocation.proceed()
        }
    }

    @Test
    @DisplayName("하나의 프록시, 여러 어드바이저")
    fun multiAdvisorTest2(){
        val advisor2 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice2())
        val advisor1 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice1())

        val target: ServiceInterface = ServiceImpl()
        val proxyFactory1 = ProxyFactory(target)
        proxyFactory1.addAdvisor(advisor2)
        proxyFactory1.addAdvisor(advisor1)
        val proxy = proxyFactory1.proxy as ServiceInterface

        proxy.save()
    }
}