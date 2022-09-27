package com.example.learning.dto

class EmployeeDto {
        var id: Long? = null

        var name: String? = null

        var email: String? = null

        var phone: String? = null

        var address: String? = null

        var salary: Double? = null

        var departmentId: Long? = null

    constructor(id: Long, name: String){
        this.id = id
        this.name = name
    }
}