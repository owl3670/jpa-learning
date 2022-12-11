package com.spring.advanced.proxy.advisor

import com.spring.advanced.proxy.common.advice.TimeAdvice
import com.spring.advanced.proxy.common.service.ServiceImpl
import com.spring.advanced.proxy.common.service.ServiceInterface
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.aop.ClassFilter
import org.springframework.aop.MethodMatcher
import org.springframework.aop.Pointcut
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import java.lang.reflect.Method

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

    @Test
    @DisplayName("직접 만든 포인트컷")
    fun advisorTest2(){
        val target = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        val advisor = DefaultPointcutAdvisor(MyPointcut(), TimeAdvice())
        proxyFactory.addAdvisor(advisor)
        val proxy = proxyFactory.proxy as ServiceInterface

        proxy.save()
        proxy.find()
    }

    companion object {
        class MyPointcut : Pointcut {
            override fun getClassFilter(): ClassFilter {
                return ClassFilter.TRUE
            }

            override fun getMethodMatcher(): MethodMatcher {
                return MyMethodMatcher()
            }
        }

        class MyMethodMatcher : MethodMatcher {
            private val logger = mu.KotlinLogging.logger {}
            private val matchName = "save"

            override fun matches(method: Method, targetClass: Class<*>): Boolean {
                val result = method.name.equals(matchName)
                logger.info("포인트컷 호출 method=${method.name}, targetClass=${targetClass}")
                logger.info("포인트컷 결과 result=${result}")
                return result
            }

            override fun isRuntime(): Boolean {
                return false
            }

            override fun matches(method: Method, targetClass: Class<*>, vararg args: Any): Boolean {
                throw UnsupportedOperationException()
            }
        }
    }
}