package com.example.learning.lifecycle

class NetworkClient {
    private var url: String? = null

    init {
        println("생성자 호출, url = $url")
    }

    fun setUrl(url: String) {
        this.url = url
    }

    fun connect() {
        println("connect: $url")
    }

    fun call(message: String) {
        println("call: $url message = $message")
    }

    fun disconnect() {
        println("close: $url")
    }

    fun init() {
        println("NetworkClient.init")
        connect()
        call("초기화 연결 메시지")
    }

    fun close() {
        println("NetworkClient.close")
        disconnect()
    }
}