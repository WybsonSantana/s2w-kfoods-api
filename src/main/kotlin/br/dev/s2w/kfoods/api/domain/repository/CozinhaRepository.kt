package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.Cozinha
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CozinhaRepository : JpaRepository<Cozinha, Long> {
    fun findTodasByNomeContaining(nome: String): List<Cozinha>
    fun findByNome(nome: String): Cozinha?
    fun existsByNome(nome: String): Boolean
}