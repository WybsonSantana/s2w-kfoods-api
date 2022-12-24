package br.dev.s2w.di.notificacao

import br.dev.s2w.di.modelo.Cliente

class NotificadorEmail : Notificador{

    override fun notificar(cliente: Cliente, mensagem: String) {
        println("Notificando %s através do email %s: %s".format(cliente.nome, cliente.email, mensagem))
    }
}