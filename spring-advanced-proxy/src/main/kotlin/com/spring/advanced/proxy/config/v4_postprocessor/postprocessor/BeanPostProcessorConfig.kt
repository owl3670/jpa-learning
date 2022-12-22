package com.spring.advanced.proxy.config.v4_postprocessor.postprocessor

import com.spring.advanced.proxy.AppV1Config
import com.spring.advanced.proxy.AppV2Config
import com.spring.advanced.proxy.config.v3_proxyfactory.advice.LogTraceAdvice
import com.spring.advanced.proxy.trace.logtrace.LogTrace
import org.springframework.aop.Advisor
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(AppV1Config::class, AppV2Config::class)
class BeanPostProcessorConfig {
    @Bean
    fun logTraceProxyPostProcessor(logTrace: LogTrace): PackageLogTraceProxyPostProcessor {
        return PackageLogTraceProxyPostProcessor("com.spring.advanced.proxy.app", getAdvisor(logTrace))
    }

    private fun getAdvisor(logTrace: LogTrace): Advisor {
        //pointcut
        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedNames("request*", "order*", "save*")
        //advice
        val advice = LogTraceAdvice(logTrace)
        //advisor = pointcut + advice
        return DefaultPointcutAdvisor(pointcut, advice)
    }
}