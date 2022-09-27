package com.example.learning.repository

import com.example.learning.entity.Department
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface DepartmentRepository : JpaRepository<Department, Long> {
    @Query("SELECT d FROM Department d left join fetch d.employees WHERE d.name = ?1")
    fun findByName(name: String): Department

    @Query("SELECT distinct d FROM Department d left join fetch d.employees WHERE d.parentId IN :ids")
    fun findAllByParentIds(ids: List<Long>): List<Department>
}