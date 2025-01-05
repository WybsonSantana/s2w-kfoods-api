package br.dev.s2w.kfoods.api.domain.service

import br.dev.s2w.kfoods.api.domain.exception.EntityNotFoundException
import br.dev.s2w.kfoods.api.domain.model.Restaurant
import br.dev.s2w.kfoods.api.domain.repository.RestaurantRepository
import org.springframework.stereotype.Service

@Service
class RestaurantRegisterService(
    private val cuisineRegister: CuisineRegisterService,
    private val restaurantRepository: RestaurantRepository
) {

    private val restaurantNotFoundMessage = { restaurantId: Long -> "There is no restaurant registration with the code $restaurantId" }

    fun find(restaurantId: Long): Restaurant {
        return restaurantRepository.findById(restaurantId).orElseThrow {
            EntityNotFoundException(restaurantNotFoundMessage(restaurantId))
        }
    }

    fun save(restaurant: Restaurant): Restaurant {
        val cuisineId = restaurant.cuisine?.id
        val currentCuisine = cuisineRegister.find(cuisineId!!)

        restaurant.copy(cuisine = currentCuisine).also {
            return restaurantRepository.save(it)
        }
    }

}
