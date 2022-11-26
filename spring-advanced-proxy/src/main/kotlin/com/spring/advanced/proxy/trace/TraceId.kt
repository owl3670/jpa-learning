package com.spring.advanced.proxy.trace

import java.util.*

class TraceId {
    val id: String
    val level: Int

    constructor() {
        this.id = createId()
        this.level = 0
    }

    constructor(id: String, level: Int) {
        this.id = id
        this.level = level
    }

    private fun createId(): String {
        return UUID.randomUUID().toString().substring(0, 8)
    }

    fun createNextId(): TraceId {
        return TraceId(id, level + 1)
    }

    fun createPrevId(): TraceId {
        return TraceId(id, level - 1)
    }

    fun isFirstLevel(): Boolean {
        return level == 0
    }
}