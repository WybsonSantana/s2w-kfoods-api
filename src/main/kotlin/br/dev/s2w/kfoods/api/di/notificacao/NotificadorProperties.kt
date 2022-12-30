package br.dev.s2w.kfoods.api.di.notificacao

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("notificador.email")
class NotificadorProperties {

    /**
     * Host do servidor de email
     */
    var host: String? = null

    /**
     * Porta do servidor de email
     */
    var port: Int = 25
}