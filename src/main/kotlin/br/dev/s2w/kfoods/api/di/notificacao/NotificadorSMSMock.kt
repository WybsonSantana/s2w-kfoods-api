package br.dev.s2w.kfoods.api.di.notificacao

import br.dev.s2w.kfoods.api.di.modelo.Cliente
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Profile("dev")
@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
class NotificadorSMSMock : Notificador {

    init {
        println("NotificadorSMSMock")
    }

    override fun notificar(cliente: Cliente, mensagem: String) {
        println("Deveria notifcar ${cliente.nome} através do telefone ${cliente.telefone}: $mensagem")
    }
}