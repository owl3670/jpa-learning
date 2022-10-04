package com.example.learning.member

import org.springframework.stereotype.Component

@Component
class MemoryMemberRepository: MemberRepository {
    override fun save(member: Member) {
        store[member.getId()] = member
    }

    override fun findById(id: Long): Member? {
        return store[id]
    }

    companion object {
        val store = HashMap<Long, Member>()
    }
}