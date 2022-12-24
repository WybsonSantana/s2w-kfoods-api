package br.dev.s2w.di

import br.dev.s2w.di.modelo.Cliente
import br.dev.s2w.di.notificacao.Notificador
import br.dev.s2w.di.notificacao.NotificadorSMS
import br.dev.s2w.di.service.AtivacaoClienteService

fun main(args: Array<String>) {
    val fulano = Cliente("Fulano de Tal", "fulanodetal@s2w.dev.br", "+55 99 98888-7777")
    val beltrano = Cliente("Bel Trano", "beltrano@s2w.dev.br", "+55 88 97777-6666")

    val notificador: Notificador = NotificadorSMS()

    val ativacaoCliente = AtivacaoClienteService(notificador)
ativacaoCliente.ativar(fulano)
    ativacaoCliente.ativar(beltrano)
}