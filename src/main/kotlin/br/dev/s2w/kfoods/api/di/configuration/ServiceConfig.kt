package br.dev.s2w.kfoods.api.di.configuration

import br.dev.s2w.kfoods.api.di.notificacao.Notificador
import br.dev.s2w.kfoods.api.di.service.AtivacaoClienteService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceConfig {

    @Bean
    fun ativacaoClienteService(notificador: Notificador): AtivacaoClienteService {
        return AtivacaoClienteService(notificador)
    }
}