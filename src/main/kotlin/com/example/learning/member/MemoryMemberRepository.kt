package com.example.learning.member

val store = HashMap<Long, Member>()

class MemoryMemberRepository: MemberRepository {
    override fun save(member: Member) {
        store[member.getId()] = member
    }

    override fun findById(id: Long): Member? {
        return store[id]
    }
}