package com.spring.advanced.proxy.config.v1_proxy.concrete_proxy

import com.spring.advanced.proxy.app.v2.OrderControllerV2
import com.spring.advanced.proxy.trace.TraceStatus
import com.spring.advanced.proxy.trace.logtrace.LogTrace

class OrderControllerConcreteProxy(
    private val target: OrderControllerV2,
    private val logTrace: LogTrace
): OrderControllerV2(null) {
    override fun request(itemId: String): String {
        var status: TraceStatus? = null
        try{
            status = logTrace.begin("OrderController.request()")
            val result = target.request(itemId)
            logTrace.end(status)
            return result
        }catch (e: Exception){
            logTrace.exception(status!!, e)
            throw e
        }
    }
}