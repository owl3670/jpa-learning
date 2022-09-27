package com.example.learning.service

import com.example.learning.dto.DepartmentDto
import com.example.learning.model.Department
import com.example.learning.repository.DepartmentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TestService (@Autowired val departmentRepository: DepartmentRepository) {
    fun test(name: String, depth: Int): DepartmentDto{
        val department = departmentRepository.findByName(name)

        val parentIds : ArrayList<Long> = ArrayList<Long>()
        parentIds.add(department.id!!)
        val deapthMap : HashMap<Int, List<Department>> = HashMap<Int, List<Department>>()
        for(i in 1..depth){
            deapthMap[i] = departmentRepository.findAllByParentIds(parentIds)
            parentIds.clear()
            deapthMap[i]!!.forEach {
                parentIds.add(it.id!!)
            }
            if (parentIds.isEmpty()) {
                break
            }
        }

        return DepartmentDto(department, deapthMap, 0)
    }
}