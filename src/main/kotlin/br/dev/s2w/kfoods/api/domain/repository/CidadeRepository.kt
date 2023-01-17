package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.Cidade

interface CidadeRepository {
    fun listar(): List<Cidade>
    fun buscar(cidadeId: Long): Cidade?
    fun salvar(cidade: Cidade): Cidade
    fun remover(cidadeId: Long)

}