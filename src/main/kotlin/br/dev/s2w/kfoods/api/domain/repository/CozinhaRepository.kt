package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.Cozinha
import org.springframework.stereotype.Component

@Component
interface CozinhaRepository {
    fun listar(): List<Cozinha>
    fun consultarPorNome(nome: String): List<Cozinha>
    fun buscar(cozinhaId: Long): Cozinha?
    fun salvar(cozinha: Cozinha): Cozinha
    fun remover(cozinhaId: Long)

}