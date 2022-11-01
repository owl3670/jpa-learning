package com.spring.advanced.trace.logtrace

import com.spring.advanced.trace.TraceId
import com.spring.advanced.trace.TraceStatus
import com.spring.advanced.trace.hellotrace.HelloTraceV2
import mu.KotlinLogging

class FiledLogTrace : LogTrace {
    private val logger = KotlinLogging.logger {}
    private var traceIdHolder: TraceId? = null

    override fun begin(message: String): TraceStatus {
        this.syncTraceId()
        val traceId = traceIdHolder!!
        val startTimeMs = System.currentTimeMillis()
        logger.info("[${traceId.id}] ${addSpace(FiledLogTrace.START_PREFIX, traceId.level)}$message")
        return TraceStatus(traceId, startTimeMs, message)
    }

    private fun syncTraceId() {
        traceIdHolder = traceIdHolder?.createNextId() ?: TraceId()
    }

    private fun releaseTraceId() {
        traceIdHolder = if (traceIdHolder!!.isFirstLevel()){
            null
        } else {
            traceIdHolder!!.createPrevId()
        }
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
            logger.info("[${traceId.id}] ${addSpace(FiledLogTrace.COMPLETE_PREFIX, traceId.level)}${status.message} time=${elapsedTimeMs}ms")
        }else{
            logger.info("[${traceId.id}] ${addSpace(FiledLogTrace.EX_PREFIX, traceId.level)}${status.message} time=${elapsedTimeMs}ms ex=${e}")
        }

        this.releaseTraceId()
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