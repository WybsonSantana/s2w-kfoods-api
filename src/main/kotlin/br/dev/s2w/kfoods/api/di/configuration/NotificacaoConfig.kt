package br.dev.s2w.kfoods.api.di.configuration

import br.dev.s2w.kfoods.api.di.notificacao.Notificador
import br.dev.s2w.kfoods.api.di.notificacao.NotificadorEmail
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NotificacaoConfig {

    @Bean
    fun notificadorEmail(): Notificador {
        val notificador = NotificadorEmail("smtp@s2w.dev.br")
        notificador.setCaixaAlta(true)

        return notificador
    }
}