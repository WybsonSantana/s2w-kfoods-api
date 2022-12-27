package br.dev.s2w.kfoods.api.di.service

import br.dev.s2w.kfoods.api.di.modelo.Cliente
import br.dev.s2w.kfoods.api.di.notificacao.Notificador
import org.springframework.stereotype.Component

class AtivacaoClienteService(notificador: Notificador) {

    private val notificador: Notificador

    init {
        this.notificador = notificador
        println("AtivacaoClienteService: $notificador")
    }

    fun ativar(cliente: Cliente) {
        cliente.ativar()
        notificador.notificar(cliente, "Seu cadastro no sistema agora está ativo!")
    }

}