package com.example.spring.advanced.aop.internalcall

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * 참고 : 생성자 주입은 순환 사이클을 만들기 때문에 실패한다.
 */
@Component
class CallServiceV1 {
    private val logger = mu.KotlinLogging.logger {}

    private lateinit var callServiceV1: CallServiceV1

    @Autowired
    fun setCallServiceV1(callServiceV1: CallServiceV1) {
        this.callServiceV1 = callServiceV1
    }

    fun external() {
        logger.info("call external")
        callServiceV1.internal()
    }

    fun internal() {
        logger.info("call internal")
    }
}