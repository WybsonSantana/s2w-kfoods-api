package br.dev.s2w.di.service

import br.dev.s2w.di.modelo.Cliente
import br.dev.s2w.di.notificacao.Notificador

data class AtivacaoClienteService(val notificador: Notificador) {

    fun ativar(cliente: Cliente) {
        cliente.ativar()
        notificador.notificar(cliente, "Seu cadastro no sistema agora está ativo!")
    }

}