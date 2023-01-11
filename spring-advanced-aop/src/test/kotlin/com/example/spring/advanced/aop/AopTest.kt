package com.example.spring.advanced.aop

import com.example.spring.advanced.aop.order.OrderRepository
import com.example.spring.advanced.aop.order.OrderService
import com.example.spring.advanced.aop.order.aop.AspectV1
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(AspectV1::class)
@SpringBootTest
class AopTest {
    private val logger = mu.KotlinLogging.logger {}

    @Autowired
    var orderService : OrderService? = null

    @Autowired
    var orderRepository : OrderRepository? = null

    @Test
    fun aopInfo(){
        logger.info("isAopProxy, orderService : {}", AopUtils.isAopProxy(orderService))
        logger.info("isAopProxy, orderRepository : {}", AopUtils.isAopProxy(orderRepository))
    }

    @Test
    fun success(){
        orderService?.orderItem("itemA")
    }

    @Test
    fun exception(){
        assertThatThrownBy { orderService?.orderItem("ex") }
            .isInstanceOf(IllegalStateException::class.java)
    }
}