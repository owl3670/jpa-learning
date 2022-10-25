package com.spring.basic.singleton

class StatefulService {
    private var price : Int = 0 // 상태를 유지하는 필드

    fun order(name: String, price: Int) {
        println("name = $name price = $price")
        this.price = price // 여기가 문제!
    }

     fun getPrice(): Int{
         return price;
     }
}