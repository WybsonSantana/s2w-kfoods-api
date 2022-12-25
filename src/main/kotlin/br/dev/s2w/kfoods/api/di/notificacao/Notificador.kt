package br.dev.s2w.kfoods.api.di.notificacao

import br.dev.s2w.kfoods.api.di.modelo.Cliente

interface Notificador {
    fun notificar(cliente: Cliente, mensagem: String)
}