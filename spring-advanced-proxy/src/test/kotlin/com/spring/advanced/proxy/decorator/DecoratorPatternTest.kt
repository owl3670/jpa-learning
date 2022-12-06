package com.spring.advanced.proxy.decorator

import com.spring.advanced.proxy.decorator.code.DecoratorPatternClient
import com.spring.advanced.proxy.decorator.code.MessageDecorator
import com.spring.advanced.proxy.decorator.code.RealComponent
import com.spring.advanced.proxy.decorator.code.TimeDecorator
import org.junit.jupiter.api.Test

class DecoratorPatternTest {
    @Test
    fun noDecorator(){
        val component = RealComponent()
        val client = DecoratorPatternClient(component)
        client.execute()
    }

    @Test
    fun decorator(){
        val component = RealComponent()
        val messageDecorator = MessageDecorator(component)
        val client = DecoratorPatternClient(messageDecorator)
        client.execute()
    }

    @Test
    fun decorator2(){
        val component = RealComponent()
        val messageDecorator = MessageDecorator(component)
        val timeDecorator = TimeDecorator(messageDecorator)
        val client = DecoratorPatternClient(timeDecorator)
        client.execute()
    }
}