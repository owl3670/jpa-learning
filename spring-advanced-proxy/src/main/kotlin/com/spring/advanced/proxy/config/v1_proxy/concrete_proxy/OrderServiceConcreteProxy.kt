package com.spring.advanced.proxy.config.v1_proxy.concrete_proxy

import com.spring.advanced.proxy.app.v2.OrderServiceV2
import com.spring.advanced.proxy.trace.TraceStatus
import com.spring.advanced.proxy.trace.logtrace.LogTrace

class OrderServiceConcreteProxy(
    private val target: OrderServiceV2,
    private val logTrace: LogTrace
) : OrderServiceV2(null) {
    override fun orderItem(itemId: String) {
        var status: TraceStatus? = null
        try {
            status = logTrace.begin("OrderService.orderItem()")
            target.orderItem(itemId)
            logTrace.end(status)
        } catch (e: Exception) {
            logTrace.exception(status!!, e)
            throw e
        }
    }
}