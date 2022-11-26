package com.spring.advanced.proxy.config.v1_proxy.interface_proxy

import com.spring.advanced.proxy.app.v1.OrderRepositoryV1
import com.spring.advanced.proxy.trace.TraceStatus
import com.spring.advanced.proxy.trace.logtrace.LogTrace

class OrderRepositoryInterfaceProxy(
    private val target: OrderRepositoryV1,
    private val logTrace: LogTrace
) : OrderRepositoryV1 {
    override fun save(itemId: String) {
        var status: TraceStatus? = null
        try {
            status = logTrace.begin("OrderRepository.save()")
            target.save(itemId)
            logTrace.end(status)
        } catch (e: Exception) {
            logTrace.exception(status!!, e)
            throw e
        }
    }
}