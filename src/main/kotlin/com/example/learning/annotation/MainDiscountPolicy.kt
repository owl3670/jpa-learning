package com.example.learning.annotation

import org.springframework.beans.factory.annotation.Qualifier

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.ANNOTATION_CLASS
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Qualifier("mainDiscountPolicy")
annotation class MainDiscountPolicy()
