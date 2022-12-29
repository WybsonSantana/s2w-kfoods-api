package br.dev.s2w.kfoods.api.di.notificacao

import br.dev.s2w.kfoods.api.di.modelo.Cliente
import org.springframework.stereotype.Component

@TipoDoNotificador(value = NivelUrgencia.URGENTE)
@Component
class NotificadorSMS : Notificador {

    override fun notificar(cliente: Cliente, mensagem: String) {
        println("Notificando ${cliente.nome} através do telefone ${cliente.telefone}: $mensagem")
    }
}