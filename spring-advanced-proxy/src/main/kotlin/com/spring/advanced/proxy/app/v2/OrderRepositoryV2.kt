package com.spring.advanced.proxy.app.v2

class OrderRepositoryV2 {
    fun save(itemId: String) {
        if (itemId == "ex"){
            throw IllegalStateException("예외 발생")
        }
        sleep(1000)
    }

    private fun sleep(millis: Long){
        try{
            Thread.sleep(millis)
        } catch (e: InterruptedException){
            e.printStackTrace()
        }
    }
}