package com.spring.advanced.trace.template.code

import mu.KotlinLogging

class SubClassLogic2 : AbstractTemplate() {
    private val logger = KotlinLogging.logger {}

    override fun call() {
        logger.info { "logic2 start" }
    }
}