package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.City

interface CityRepository {
    fun list(): List<City>
    fun search(cityId: Long?): City?
    fun save(city: City): City
    fun remove(cityId: Long)
}