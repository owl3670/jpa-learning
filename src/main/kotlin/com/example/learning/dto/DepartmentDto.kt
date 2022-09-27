package com.example.learning.dto

import com.example.learning.entity.Department
import java.util.stream.Collectors

class DepartmentDto {

    var id: Long? = null
    var name: String? = null
    var subs: List<DepartmentDto>? = null
    var employees: List<EmployeeDto>? = null

    constructor(department: Department, map: HashMap<Int, List<Department>>, depth: Int) {
        this.id = department.id
        this.name = department.name
        this.subs =
            map[depth + 1]?.stream()?.filter { it.parentId == department.id }?.map { DepartmentDto(it, map, depth + 1) }
                ?.collect(Collectors.toList())
        this.employees = department.employees?.stream()?.map { EmployeeDto(it) }?.collect(Collectors.toList())
    }
}