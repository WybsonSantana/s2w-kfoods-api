package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.Restaurant

interface RestaurantRepository {
    fun list(): List<Restaurant>
    fun search(id: Long): Restaurant
    fun save(restaurant: Restaurant): Restaurant
    fun remove(restaurant: Restaurant)
}