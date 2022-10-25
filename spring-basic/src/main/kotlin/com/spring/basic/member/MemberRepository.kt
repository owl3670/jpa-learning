package com.spring.basic.member

interface MemberRepository {
    fun save(member: Member)
    fun findById(id: Long): Member?
}