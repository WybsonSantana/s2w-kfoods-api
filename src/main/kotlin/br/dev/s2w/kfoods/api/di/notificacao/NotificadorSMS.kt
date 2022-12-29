package br.dev.s2w.kfoods.api.di.notificacao

import br.dev.s2w.kfoods.api.di.modelo.Cliente
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Profile("prod")
@TipoDoNotificador(value = NivelUrgencia.URGENTE)
@Component
class NotificadorSMS : Notificador {

    init {
        println("NotificadorSMS")
    }

    override fun notificar(cliente: Cliente, mensagem: String) {
        println("Notificando ${cliente.nome} através do telefone ${cliente.telefone}: $mensagem")
    }
}