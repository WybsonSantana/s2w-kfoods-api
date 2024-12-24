package br.dev.s2w.kfoods.api.domain.service

import br.dev.s2w.kfoods.api.domain.exception.EntityNotFoundException
import br.dev.s2w.kfoods.api.domain.model.Restaurant
import br.dev.s2w.kfoods.api.domain.repository.CuisineRepository
import br.dev.s2w.kfoods.api.domain.repository.RestaurantRepository
import org.springframework.stereotype.Service

@Service
class RestaurantRegisterService(
    private val cuisineRepository: CuisineRepository,
    private val restaurantRepository: RestaurantRepository
) {

    fun save(restaurant: Restaurant): Restaurant {
        val cuisineId = restaurant.cuisine?.id
        val currentCuisine = cuisineRepository.search(cuisineId)
            ?: throw EntityNotFoundException("There is no cuisine registration with the code $cuisineId")

        val currentRestaurant = restaurant.copy(cuisine = currentCuisine)

        return restaurantRepository.save(currentRestaurant)
    }
}