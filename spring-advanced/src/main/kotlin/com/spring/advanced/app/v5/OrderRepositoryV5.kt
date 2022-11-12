package com.spring.advanced.app.v5

import com.spring.advanced.trace.callback.TraceTemplate
import com.spring.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV5(
    trace: LogTrace
) {
    private val template: TraceTemplate = TraceTemplate(trace)

    fun save(itemId: String) {
        template.execute("OrderRepositoryV5.save()") {
            if(itemId == "ex") {
                throw IllegalStateException("예외 발생")
            }
            this.sleep(1000)
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
