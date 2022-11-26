package com.spring.advanced.app.v2

import com.spring.advanced.trace.TraceId
import com.spring.advanced.trace.TraceStatus
import com.spring.advanced.trace.hellotrace.HelloTraceV2
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV2(
    private val trace: HelloTraceV2
) {
    fun save(traceId: TraceId, itemId: String) {
        var status: TraceStatus? = null
        try {
            status = trace.beginSync(traceId, "OrderRepository.save()")
            if (itemId == "ex") {
                throw IllegalStateException("예외 발생")
            }
            this.sleep(1000)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }

    private fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
