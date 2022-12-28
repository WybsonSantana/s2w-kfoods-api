package br.dev.s2w.kfoods.api.di.service

import br.dev.s2w.kfoods.api.di.modelo.Cliente
import br.dev.s2w.kfoods.api.di.notificacao.Notificador
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AtivacaoClienteService {

    @Autowired(required = false)
    private var notificador: Notificador? = null

    fun ativar(cliente: Cliente) {
        cliente.ativar()

        if (notificador != null) {
            notificador!!.notificar(cliente, "Seu cadastro no sistema agora está ativo!")
        } else {
            println("O cliente ${cliente.nome} foi ativado no sistema, mas não foi notificado devido a falta de um serviço de notificação.")
        }
    }

}