package com.spring.advanced.trace.strategy.code.strategy

import mu.KotlinLogging

class StrategyLogic2 : Strategy {
    private val logger = KotlinLogging.logger {}

    override fun call() {
        logger.info { "execute logic2" }
    }
}