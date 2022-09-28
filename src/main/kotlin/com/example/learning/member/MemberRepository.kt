package com.example.learning.member

interface MemberRepository {
    fun save(member: Member)
    fun findById(id: Long): Member?
}