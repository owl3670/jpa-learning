package com.spring.basic.order

class Order(
    private val memberId: Long,
    private val itemName: String,
    private val itemPrice: Int,
    private val discountPrice: Int
) {
    fun calculatePrice(): Int {
        return itemPrice - discountPrice
    }

    fun getMemberId(): Long {
        return memberId
    }

    fun getItemName(): String {
        return itemName
    }

    fun getItemPrice(): Int {
        return itemPrice
    }

    fun getDiscountPrice(): Int {
        return discountPrice
    }

    override fun toString(): String {
        return "Order(memberId=$memberId, itemName='$itemName', itemPrice=$itemPrice, discountPrice=$discountPrice)"
    }
}