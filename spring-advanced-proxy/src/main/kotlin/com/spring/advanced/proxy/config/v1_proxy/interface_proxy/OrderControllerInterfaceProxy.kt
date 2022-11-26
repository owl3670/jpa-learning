package com.spring.advanced.proxy.config.v1_proxy.interface_proxy

import com.spring.advanced.proxy.app.v1.OrderControllerV1
import com.spring.advanced.proxy.trace.TraceStatus
import com.spring.advanced.proxy.trace.logtrace.LogTrace

class OrderControllerInterfaceProxy(
    private val target: OrderControllerV1,
    private val logTrace: LogTrace
) : OrderControllerV1 {
    override fun request(itemId: String): String {
        var status: TraceStatus? = null
        try {
            status = logTrace.begin("OrderController.request()")
            val result: String = target.request(itemId)
            logTrace.end(status)
            return result
        } catch (e: Exception) {
            logTrace.exception(status!!, e)
            throw e
        }
    }

    override fun noLog(): String {
        return target.noLog()
    }
}