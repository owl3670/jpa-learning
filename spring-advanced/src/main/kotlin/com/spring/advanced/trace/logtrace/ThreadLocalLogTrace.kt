package com.spring.advanced.trace.logtrace

import com.spring.advanced.trace.TraceId
import com.spring.advanced.trace.TraceStatus
import mu.KotlinLogging

class ThreadLocalLogTrace: LogTrace {
    private val logger = KotlinLogging.logger {}
    private val traceIdHolder = ThreadLocal<TraceId>()

    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId = traceIdHolder.get()
        val startTimeMs = System.currentTimeMillis()
        logger.info("[${traceId.id}] ${addSpace(ThreadLocalLogTrace.START_PREFIX, traceId.level)}$message")
        return TraceStatus(traceId, startTimeMs, message)
    }

    override fun end(status: TraceStatus) {
        this.complete(status, null)
    }

    override fun exception(status: TraceStatus, e: Exception) {
        this.complete(status, e)
    }

    private fun complete(status: TraceStatus, e: Exception?) {
        val endTimeMs = System.currentTimeMillis()
        val elapsedTimeMs = endTimeMs - status.startTimeMs
        val traceId = status.traceId
        if(e == null){
            logger.info("[${traceId.id}] ${addSpace(ThreadLocalLogTrace.COMPLETE_PREFIX, traceId.level)}${status.message} time=${elapsedTimeMs}ms")
        }else{
            logger.info("[${traceId.id}] ${addSpace(ThreadLocalLogTrace.EX_PREFIX, traceId.level)}${status.message} time=${elapsedTimeMs}ms ex=${e}")
        }

        this.releaseTraceId()
    }

    private fun releaseTraceId() {
        val traceId = traceIdHolder.get()
        if (traceId.isFirstLevel()){
            traceIdHolder.remove()
        }else{
            traceIdHolder.set(traceId.createPrevId())
        }
    }

    private fun syncTraceId() {
        val traceId = traceIdHolder.get()
        if (traceId == null) {
            traceIdHolder.set(TraceId())
        } else{
            traceIdHolder.set(traceId.createNextId())
        }
    }

    private fun addSpace(prefix: String, level: Int): String {
        val sb = StringBuilder()
        for (i in 0 until level) {
            sb.append(if (i == level - 1) "|$prefix" else "|   ")
        }
        return sb.toString()
    }

    companion object {
        private const val START_PREFIX = "-->"
        private const val COMPLETE_PREFIX = "<--"
        private const val EX_PREFIX = "<X-"
    }
}