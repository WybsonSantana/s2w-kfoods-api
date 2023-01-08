package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.Cozinha

interface CozinhaRepository {
    fun listar(): List<Cozinha>
    fun buscar(id: Long): Cozinha?
    fun salvar(cozinha: Cozinha): Cozinha
        fun remover(cozinhaId: Long)

}