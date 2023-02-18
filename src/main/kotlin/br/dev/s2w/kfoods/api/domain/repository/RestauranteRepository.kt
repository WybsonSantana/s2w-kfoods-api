package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.Restaurante
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
interface RestauranteRepository : JpaRepository<Restaurante, Long> {
    fun findByTaxaFreteBetween(taxaInicial: BigDecimal, taxaFinal: BigDecimal): List<Restaurante>
    fun findByNomeContainingAndCozinhaId(nome: String, cozinhaId: Long): List<Restaurante>
}