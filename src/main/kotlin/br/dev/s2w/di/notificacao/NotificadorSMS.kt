package br.dev.s2w.di.notificacao

import br.dev.s2w.di.modelo.Cliente

class NotificadorSMS : Notificador{

    override fun notificar(cliente: Cliente, mensagem: String) {
        println("Notificando %s através do telefone %s: %s".format(cliente.nome, cliente.telefone, mensagem))
    }
}