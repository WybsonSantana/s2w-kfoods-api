package br.dev.s2w.kfoods.api.di.notificacao

import br.dev.s2w.kfoods.api.di.modelo.Cliente
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Qualifier("normal")
@Component
class NotificadorEmail : Notificador {

    override fun notificar(cliente: Cliente, mensagem: String) {
        println("Notificando ${cliente.nome} através do email ${cliente.email}: $mensagem")
    }
}
