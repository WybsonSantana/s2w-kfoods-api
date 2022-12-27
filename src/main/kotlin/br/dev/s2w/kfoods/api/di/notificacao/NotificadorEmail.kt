package br.dev.s2w.kfoods.api.di.notificacao

import br.dev.s2w.kfoods.api.di.modelo.Cliente

class NotificadorEmail(private val hostServidorSmtp: String) : Notificador {

    private var caixaAlta = false

    init {
        println("NotificadorEmail")
    }

    override fun notificar(cliente: Cliente, mensagem: String) {
        println("Notificando ${cliente.nome} através do email ${cliente.email} usando o servidor SMTP $hostServidorSmtp: ${if (caixaAlta) mensagem.uppercase() else mensagem}")
    }

    fun setCaixaAlta(caixaAlta: Boolean) {
        this.caixaAlta = caixaAlta
    }
}
