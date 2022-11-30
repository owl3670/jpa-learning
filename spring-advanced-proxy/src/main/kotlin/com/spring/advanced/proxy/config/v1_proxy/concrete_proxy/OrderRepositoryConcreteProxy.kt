package com.spring.advanced.proxy.config.v1_proxy.concrete_proxy

import com.spring.advanced.proxy.app.v2.OrderRepositoryV2
import com.spring.advanced.proxy.trace.TraceStatus
import com.spring.advanced.proxy.trace.logtrace.LogTrace

class OrderRepositoryConcreteProxy(
    private val target: OrderRepositoryV2,
    private val logTrace: LogTrace
): OrderRepositoryV2() {
    override fun save(itemId: String) {
        var status: TraceStatus? = null
        try{
            status = logTrace.begin("OrderRepository.save()")
            target.save(itemId)
            logTrace.end(status)
        }catch (e: Exception){
            logTrace.exception(status!!, e)
            throw e
        }
    }
}