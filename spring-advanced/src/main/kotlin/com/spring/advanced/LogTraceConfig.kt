package com.spring.advanced

import com.spring.advanced.trace.logtrace.FieldLogTrace
import com.spring.advanced.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LogTraceConfig {
    @Bean
    fun logTrace(): LogTrace {
        return FieldLogTrace()
    }
}