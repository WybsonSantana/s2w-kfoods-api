package br.dev.s2w.kfoods.api.di.notification

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@ConstructorBinding
@ConfigurationProperties(prefix = "notifier.email")
data class NotifierProperties(
    /**
     * Email Server host
     */
    var serverHost: String,

    /**
     * Email server  port
     */
    var serverPort: Int = 25
)