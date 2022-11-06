package com.spring.advanced.trace.template.code

import mu.KotlinLogging

class SubClassLogic1 : AbstractTemplate() {
    private val logger = KotlinLogging.logger {}

    override fun call() {
        logger.info { "logic1 start" }
    }
}