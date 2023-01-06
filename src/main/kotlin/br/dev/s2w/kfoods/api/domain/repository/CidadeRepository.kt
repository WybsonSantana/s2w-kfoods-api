package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.Cidade

interface CidadeRepository {
    fun listar(): List<Cidade>
    fun buscar(id: Long): Cidade
    fun salvar(cidade: Cidade): Cidade
    fun remover(cidade: Cidade)

}