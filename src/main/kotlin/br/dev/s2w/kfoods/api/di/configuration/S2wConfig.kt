package br.dev.s2w.kfoods.api.di.configuration

import br.dev.s2w.kfoods.api.di.notificacao.Notificador
import br.dev.s2w.kfoods.api.di.notificacao.NotificadorEmail
import br.dev.s2w.kfoods.api.di.service.AtivacaoClienteService
import org.springframework.context.annotation.Bean

//@Configuration
class S2wConfig {

    @Bean
    fun notificadorEmail(): Notificador {
        val notificador = NotificadorEmail("smtp@s2w.dev.br")
        notificador.setCaixaAlta(true)

        return notificador
    }

    @Bean
    fun ativacaoClienteService(): AtivacaoClienteService {
        return AtivacaoClienteService(notificadorEmail())
    }
}