package br.dev.s2w.kfoods.api.di.notificacao

import br.dev.s2w.kfoods.api.di.modelo.Cliente
import org.springframework.stereotype.Component

@Component
class NotificadorEmail : Notificador {

    init {
        println("NotificadorEmail")
    }

    override fun notificar(cliente: Cliente, mensagem: String) {
        println("Notificando %s através do email %s: %s".format(cliente.nome, cliente.email, mensagem))
    }
}