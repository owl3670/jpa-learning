package com.spring.advanced.proxy.config.v6_aop

import com.spring.advanced.proxy.AppV1Config
import com.spring.advanced.proxy.AppV2Config
import com.spring.advanced.proxy.config.v6_aop.aspect.LogTraceAspect
import com.spring.advanced.proxy.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(AppV1Config::class, AppV2Config::class)
class AopConfig {
    @Bean
    fun logTraceAspect(logTrace: LogTrace): LogTraceAspect {
        return LogTraceAspect(logTrace)
    }
}