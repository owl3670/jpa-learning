package com.example.learning.model

import org.hibernate.annotations.BatchSize
import javax.persistence.*

@Entity
class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    var name: String? = null


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: Department? = null

    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    var subs: Set<Department>? = null

    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    var employees: Set<Employee>? = null

}