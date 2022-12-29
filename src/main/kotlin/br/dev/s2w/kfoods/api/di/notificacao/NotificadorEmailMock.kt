package br.dev.s2w.kfoods.api.di.notificacao

import br.dev.s2w.kfoods.api.di.modelo.Cliente
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Profile("dev")
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
class NotificadorEmailMock : Notificador {

    init {
        println("NotificadorEmailMock")
    }

    override fun notificar(cliente: Cliente, mensagem: String) {
        println("Deveria notificar ${cliente.nome} através do email ${cliente.email}: $mensagem")
    }
}