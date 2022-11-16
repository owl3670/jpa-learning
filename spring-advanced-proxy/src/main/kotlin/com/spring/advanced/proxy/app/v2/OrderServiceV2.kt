package com.spring.advanced.proxy.app.v2

class OrderServiceV2(
    private val orderRepository: OrderRepositoryV2
) {
    fun orderItem(itemId: String) {
        this.orderRepository.save(itemId)
    }
}