package com.example.learning.model

import org.hibernate.annotations.BatchSize
import javax.persistence.*

@Entity
class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    var name: String? = null

    @Column(name="parent_id")
    var parentId: Long? = null

    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    var employees: List<Employee>? = null
}