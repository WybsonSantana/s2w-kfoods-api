package br.dev.s2w.kfoods.api.di.service

import br.dev.s2w.kfoods.api.di.modelo.Cliente
import br.dev.s2w.kfoods.api.di.notificacao.NotificadorEmail
import org.springframework.stereotype.Component

@Component
data class AtivacaoClienteService(val notificador: NotificadorEmail) {

    fun ativar(cliente: Cliente) {
        cliente.ativar()
        notificador.notificar(cliente, "Seu cadastro no sistema agora está ativo!")
    }

}