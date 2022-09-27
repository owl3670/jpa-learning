package com.example.learning.dto

import com.example.learning.model.Employee

class EmployeeDto {
        var id: Long? = null

        var name: String? = null

        var email: String? = null

        var phone: String? = null

        var address: String? = null

        var salary: Double? = null

        var departmentId: Long? = null

    constructor(employee: Employee){
        this.id = employee.id
        this.name = employee.name
    }
}