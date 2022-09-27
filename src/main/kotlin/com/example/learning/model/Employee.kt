package com.example.learning.model

import javax.persistence.*

@Entity
class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var name: String? = null
    var email: String? = null
    var phone: String? = null
    var address: String? = null
    var salary: Double? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    var department: Department? = null
}