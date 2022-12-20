package com.spring.advanced.proxy.config.v4_postprocessor.postprocessor

import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.beans.factory.config.BeanPostProcessor

class PackageLogTraceProxyPostProcessor(
    private val basePackage: String,
    private val advisor: Advisor
) : BeanPostProcessor {
    private val logger = mu.KotlinLogging.logger {}

    override fun postProcessAfterInitialization(bean: Any, beanName: String) : Any{
        logger.info("param beanName={} bean={}", beanName, bean.javaClass)

        val packageName = bean.javaClass.`package`.name
        if (!packageName.startsWith(basePackage)) {
            return bean
        }

        val factory = ProxyFactory(bean)
        factory.addAdvisor(advisor)

        val proxy = factory.proxy
        logger.info("create proxy: target={} proxy={}", bean.javaClass, proxy.javaClass)
        return proxy
    }
}