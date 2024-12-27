package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.Cuisine

interface CuisineRepository {
    fun list(): List<Cuisine>
    fun findByName(name: String): List<Cuisine>
    fun search(id: Long?): Cuisine?
    fun save(cuisine: Cuisine): Cuisine
    fun remove(cuisineId: Long)
}