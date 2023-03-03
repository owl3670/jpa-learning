package com.example.spring.advanced.aop.internalcall

import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Component

@Component
class CallServiceV2(
    private val callServiceProvider : ObjectProvider<CallServiceV2>
) {
    private val logger = mu.KotlinLogging.logger {}

    fun external() {
        logger.info("call external")
        val callServiceV2 = callServiceProvider.getObject()
        callServiceV2.internal()
    }

    fun internal(){
        logger.info("call internal")
    }
}