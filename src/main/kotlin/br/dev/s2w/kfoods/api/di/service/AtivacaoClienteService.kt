package br.dev.s2w.kfoods.api.di.service

import br.dev.s2w.kfoods.api.di.event.ClienteAtivadoEvent
import br.dev.s2w.kfoods.api.di.modelo.Cliente
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class AtivacaoClienteService(private val publisherEvent: ApplicationEventPublisher) {

    fun ativar(cliente: Cliente) {
        cliente.ativar()

        publisherEvent.publishEvent(ClienteAtivadoEvent(cliente))
    }

}