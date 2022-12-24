package br.dev.s2w.di.notificacao

import br.dev.s2w.di.modelo.Cliente

interface Notificador {
fun notificar(cliente: Cliente, mensagem: String)
}