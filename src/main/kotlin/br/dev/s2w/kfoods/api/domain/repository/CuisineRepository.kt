package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.Cuisine
import org.springframework.stereotype.Repository

@Repository
interface CuisineRepository : CustomJpaRepository<Cuisine, Long> {

    fun findAllByNameContaining(name: String): List<Cuisine>

    fun findByName(name: String): Cuisine?

    fun existsByName(name: String): Boolean

}
