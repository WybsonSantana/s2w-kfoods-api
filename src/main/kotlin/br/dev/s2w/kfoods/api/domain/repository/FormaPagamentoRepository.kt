package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.FormaPagamento

interface FormaPagamentoRepository {
    fun listar(): List<FormaPagamento>
    fun buscar(formaPagamentoId: Long): FormaPagamento?
    fun salvar(formaPagamento: FormaPagamento): FormaPagamento
    fun remover(formaPagamento: FormaPagamento)

}