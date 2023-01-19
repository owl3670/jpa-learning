package com.example.spring.advanced.aop.order.aop

import org.aspectj.lang.annotation.Pointcut

class Pointcuts {
    @Pointcut("execution(* com.example.spring.advanced.aop.order..*(..))")
    fun allOrder() {
    }

    @Pointcut("execution(* *..*Service.*(..))")
    fun allService() {
    }

    @Pointcut("allOrder() && allService()")
    fun orderAndService() {
    }
}