package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.Restaurante
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
interface RestauranteRepository : JpaRepository<Restaurante, Long> {
    fun findByTaxaFreteBetween(taxaInicial: BigDecimal, taxaFinal: BigDecimal): List<Restaurante>

    //@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
    fun consultarPorNome(nome: String, @Param("id") cozinhaId: Long): List<Restaurante>

    //fun findByNomeContainingAndCozinhaId(nome: String, cozinhaId: Long): List<Restaurante>
    fun findFirstRestauranteByNomeContaining(nome: String): Restaurante?
    fun findTop2ByNomeContaining(nome: String): List<Restaurante>
    fun countByCozinhaId(cozinhaId: Long): Int

}