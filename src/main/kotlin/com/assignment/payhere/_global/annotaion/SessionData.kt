package com.assignment.payhere._global.annotaion

import org.springframework.security.core.parameters.AnnotationParameterNameDiscoverer
import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class SessionData {
}