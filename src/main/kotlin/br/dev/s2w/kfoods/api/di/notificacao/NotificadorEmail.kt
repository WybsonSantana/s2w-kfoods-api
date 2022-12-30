package br.dev.s2w.kfoods.api.di.notificacao

import br.dev.s2w.kfoods.api.di.modelo.Cliente
import org.springframework.stereotype.Component

@TipoDoNotificador(value = NivelUrgencia.SEM_URGENCIA)
@Component
class NotificadorEmail(
    private val properties: NotificadorProperties
) : Notificador {

    override fun notificar(cliente: Cliente, mensagem: String) {
        println("Host: ${properties.host}")
        println("Port: ${properties.port}")

        println("Notificando ${cliente.nome} através do email ${cliente.email}: $mensagem")
    }
}
