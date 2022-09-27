package com.example.learning.dto

import com.example.learning.model.Department
import com.example.learning.model.Employee
import java.util.stream.Collectors

class DepartmentDto {

    var id: Long? = null
    var name: String? = null
    var subs: List<DepartmentDto>? = null
    var employees: List<EmployeeDto>? = null

    constructor(id: Long?, name: String?, subs: Set<Department>?, employees: Set<Employee>?) {
        this.id = id
        this.name = name
        this.subs = subs?.stream()?.map { DepartmentDto(it.id!!, it.name!!, it.subs!!, it.employees!!) }?.collect(Collectors.toList())
        this.employees = employees?.stream()?.map { EmployeeDto(it.id!!, it.name!!) }?.collect(Collectors.toList())
    }

}