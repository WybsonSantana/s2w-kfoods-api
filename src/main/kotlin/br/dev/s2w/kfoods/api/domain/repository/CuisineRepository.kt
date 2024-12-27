package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.Cuisine
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CuisineRepository : JpaRepository<Cuisine, Long> {
    //fun findByName(name: String): List<Cuisine>
}