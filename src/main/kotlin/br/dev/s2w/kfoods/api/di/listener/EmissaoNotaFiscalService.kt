package br.dev.s2w.kfoods.api.di.listener

import br.dev.s2w.kfoods.api.di.event.ClienteAtivadoEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class EmissaoNotaFiscalService {

    @EventListener
    fun clienteAtivadoListner(event: ClienteAtivadoEvent) {
        println("Emitindo nota fiscal para ${event.cliente.nome}...")
    }
}