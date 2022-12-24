package br.dev.s2w.di.service

import br.dev.s2w.di.modelo.Cliente
import br.dev.s2w.di.modelo.Produto
import br.dev.s2w.di.notificacao.Notificador

data class EmissaoNotaFiscalService(val notificador: Notificador) {

    fun emitir(cliente: Cliente, produto: Produto) {
        println("Emissão da nota fiscal...")
        notificador.notificar(cliente, "A nota fiscal do produto %s foi emitida!".format(produto.nome))
    }
}