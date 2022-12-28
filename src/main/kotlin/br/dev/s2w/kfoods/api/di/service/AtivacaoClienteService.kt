package br.dev.s2w.kfoods.api.di.service

import br.dev.s2w.kfoods.api.di.modelo.Cliente
import br.dev.s2w.kfoods.api.di.notificacao.Notificador
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class AtivacaoClienteService {

    @Qualifier("urgente")
    @Autowired
    private var notificador: Notificador? = null

    fun ativar(cliente: Cliente) {
        cliente.ativar()

        notificador!!.notificar(cliente, "Seu cadastro no sistema agora está ativo!")
    }

}