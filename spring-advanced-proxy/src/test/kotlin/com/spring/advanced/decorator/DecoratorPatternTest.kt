package com.spring.advanced.decorator

import com.spring.advanced.decorator.code.DecoratorPatternClient
import com.spring.advanced.decorator.code.RealComponent
import org.junit.jupiter.api.Test

class DecoratorPatternTest {
    @Test
    fun noDecorator(){
        val component = RealComponent()
        val client = DecoratorPatternClient(component)
        client.execute()
    }
}