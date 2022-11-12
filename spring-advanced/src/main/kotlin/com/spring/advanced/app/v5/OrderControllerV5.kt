package com.spring.advanced.app.v5

import com.spring.advanced.trace.callback.TraceTemplate
import com.spring.advanced.trace.logtrace.LogTrace
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV5(
    private val orderService: OrderServiceV5,
    trace: LogTrace,
) {
    private val template: TraceTemplate = TraceTemplate(trace)

    @GetMapping("/v5/request")
    fun request(itemId: String): String {
        return template.execute("OrderControllerV5.request()"
        ) {
            orderService.orderItem(itemId)
            "ok"
        }
    }
}