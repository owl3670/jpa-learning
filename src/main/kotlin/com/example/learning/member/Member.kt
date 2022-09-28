package com.example.learning.member

class Member (
    private var id: Long,
    private var name: String,
    private var grade: Grade
        ){
    fun getId(): Long {
        return id
    }

    fun getName(): String {
        return name
    }

    fun getGrade(): Grade {
        return grade
    }

    fun setId(id: Long) {
        this.id = id
    }

    fun setName(name: String) {
        this.name = name
    }

    fun setGrade(grade: Grade) {
        this.grade = grade
    }
}