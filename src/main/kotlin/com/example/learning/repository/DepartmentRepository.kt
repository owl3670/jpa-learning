package com.example.learning.repository

import static com.example.learning.model.QDepartment.department

import com.example.learning.model.Department
import com.querydsl.jpa.impl.JPAQuery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DepartmentRepository : JpaRepository<Department, Long> {
    fun findByName(name: String): Department{
        return JPAQuery<Department>.select(department).from(department).where(department.name.eq(name)).fetchOne()
    }
}