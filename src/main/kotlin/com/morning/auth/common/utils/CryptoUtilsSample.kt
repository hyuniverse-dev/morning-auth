package com.morning.auth.common.utils

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

//@Configuration
//@PropertySource("classpath:application.yaml")
//@JsonIgnoreProperties("\$\$beanFactory")
//@ConfigurationProperties(prefix = "crypto")
//@Component
object CryptoUtilsSample {
    @Value("\${crypto.greeting}")
    var greeting: String = ""

    @Value("\${crypto.password}")
    var password: String = ""

    var copyGreeting: String = this.greeting

    fun banana(message: String): String {
        return "$greeting $message $copyGreeting"
    }
}
