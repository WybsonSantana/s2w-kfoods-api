package br.dev.s2w.kfoods.api.di.service

import br.dev.s2w.kfoods.api.di.modelo.Cliente
import br.dev.s2w.kfoods.api.di.notificacao.NivelUrgencia
import br.dev.s2w.kfoods.api.di.notificacao.Notificador
import br.dev.s2w.kfoods.api.di.notificacao.TipoDoNotificador
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AtivacaoClienteService {

    @field:TipoDoNotificador(value = NivelUrgencia.URGENTE)
    @Autowired
    private val notificador: Notificador? = null

    fun ativar(cliente: Cliente) {
        cliente.ativar()

        notificador!!.notificar(cliente, "Seu cadastro no sistema agora está ativo!")
    }

}