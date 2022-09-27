package com.example.learning.test

import com.example.learning.dto.DepartmentDto
import com.example.learning.model.Department
import com.example.learning.repository.DepartmentRepository
import com.querydsl.jpa.impl.JPAQuery
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Test(@Autowired val departmentRepository: DepartmentRepository) {
    private val logger = KotlinLogging.logger {}

    @GetMapping("/test")
    fun test()  {
    }
}