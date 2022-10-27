package com.spring.advanced.trace.hellotrace

import com.spring.advanced.trace.TraceId
import com.spring.advanced.trace.TraceStatus
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class HelloTraceV1 {
    val logger = KotlinLogging.logger {}

    fun begin(message: String): TraceStatus {
        val traceId = TraceId()
        val startTimeMs = System.currentTimeMillis()
        logger.info("[${traceId.id}] ${addSpace(START_PREFIX, traceId.level)}$message")
        return TraceStatus(traceId, startTimeMs, message)
    }

    fun end(status: TraceStatus) {
        this.complete(status, null)
    }

    fun exception(status: TraceStatus, e: Exception) {
        this.complete(status, e)
    }

    private fun complete(status: TraceStatus, e: Exception?) {
        val endTimeMs = System.currentTimeMillis()
        val elapsedTimeMs = endTimeMs - status.startTimeMs
        val traceId = status.traceId
        if(e == null){
            logger.info("[${traceId.id}] ${addSpace(COMPLETE_PREFIX, traceId.level)}${status.message} time=${elapsedTimeMs}ms")
        }else{
            logger.info("[${traceId.id}] ${addSpace(EX_PREFIX, traceId.level)}${status.message} time=${elapsedTimeMs}ms ex=${e}")
        }
    }

    private fun addSpace(prefix: String, level: Int): String {
        val sb = StringBuilder()
        for (i in 0 until level) {
            sb.append(if (i == level - 1) "|$prefix" else "|   ")
        }
        return sb.toString()
    }

    companion object{
        private const val START_PREFIX = "-->"
        private const val COMPLETE_PREFIX = "<--"
        private const val EX_PREFIX = "<X-"
    }
}