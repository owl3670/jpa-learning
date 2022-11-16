package com.spring.advanced.proxy.app.v2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@RequestMapping
@ResponseBody
class OrderControllerV2(
    private val orderService: OrderServiceV2
) {
    @GetMapping("/v2/request")
    fun request(itemId: String): String {
        this.orderService.orderItem(itemId)
        return "ok"
    }

    @GetMapping("/v2/no-log")
    fun noLog(): String {
        return "ok"
    }
}