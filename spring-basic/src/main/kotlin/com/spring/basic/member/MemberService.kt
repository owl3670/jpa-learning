package com.spring.basic.member

interface MemberService {
    fun join(member: Member)
    fun findMember(memberId: Long): Member?
}