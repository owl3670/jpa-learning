package com.example.learning

import com.example.learning.member.MemberRepository
import com.example.learning.member.MemoryMemberRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

@Configuration
@ComponentScan(excludeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION, classes = [Configuration::class])])
class AutoAppConfig {
    @Bean(name = ["memoryMemberRepository"])
    fun memoryMemberRepository(): MemberRepository {
        return MemoryMemberRepository()
    }
}