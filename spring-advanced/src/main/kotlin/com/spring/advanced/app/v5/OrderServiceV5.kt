package com.spring.advanced.app.v5

import com.spring.advanced.trace.callback.TraceTemplate
import com.spring.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Service

@Service
class OrderServiceV5(
    private val orderRepository: OrderRepositoryV5,
    trace: LogTrace
) {
    private val template: TraceTemplate = TraceTemplate(trace)

    fun orderItem(itemId: String) {
        template.execute("OrderServiceV5.orderItem()") {
            orderRepository.save(itemId)
        }
    }
}
