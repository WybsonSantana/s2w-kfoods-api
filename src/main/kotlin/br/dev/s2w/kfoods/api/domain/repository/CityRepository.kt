package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.City

interface CityRepository {
    fun list(): List<City>
    fun search(id: Long): City
    fun save(city: City): City
    fun remove(city: City)
}