package br.dev.s2w.kfoods.api.di.notificacao

import br.dev.s2w.kfoods.api.di.modelo.Cliente
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@TipoDoNotificador(value = NivelUrgencia.SEM_URGENCIA)
@Component
class NotificadorEmail : Notificador {

    @Value("\${notificador.email.host}")
    private val host: String? = null

    @Value("\${notificador.email.port}")
    private val port: Int? = null

    override fun notificar(cliente: Cliente, mensagem: String) {
        println("Host: $host")
        println("Port: $port")

        println("Notificando ${cliente.nome} através do email ${cliente.email}: $mensagem")
    }
}
