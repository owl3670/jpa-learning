package com.example.learning.controller

import com.example.learning.dto.DepartmentDto
import com.example.learning.repository.DepartmentRepository
import com.example.learning.service.TestService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(@Autowired val testService: TestService) {
    private val logger = KotlinLogging.logger {}

    @GetMapping("/test/{name}/{depth}")
    fun test(@PathVariable name: String, @PathVariable depth: Int): DepartmentDto {
        return testService.test(name, depth)
    }
}