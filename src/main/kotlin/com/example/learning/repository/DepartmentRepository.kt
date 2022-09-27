package com.example.learning.repository

import com.example.learning.model.Department
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface DepartmentRepository : JpaRepository<Department, Long> {
    @Query("SELECT d FROM Department d join fetch d.employees WHERE d.name = ?1")
    fun findByName(name: String): Department

    @Query("SELECT d FROM Department d join fetch d.employees WHERE d.parentId IN :ids")
    fun findAllByParentIds(ids: List<Long>): List<Department>
}