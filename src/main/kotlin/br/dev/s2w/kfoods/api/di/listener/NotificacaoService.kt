package br.dev.s2w.kfoods.api.di.listener

import br.dev.s2w.kfoods.api.di.event.ClienteAtivadoEvent
import br.dev.s2w.kfoods.api.di.notificacao.NivelUrgencia
import br.dev.s2w.kfoods.api.di.notificacao.Notificador
import br.dev.s2w.kfoods.api.di.notificacao.TipoDoNotificador
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class NotificacaoService {

    @field:TipoDoNotificador(value = NivelUrgencia.SEM_URGENCIA)
    @Autowired
    private val notificador: Notificador? = null

    @EventListener
    fun clienteAtivadoListener(event: ClienteAtivadoEvent) {
        notificador?.notificar(event.cliente, "Seu cadastro no sistema agora está ativo!")
    }
}