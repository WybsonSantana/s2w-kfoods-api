package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.domain.model.Cuisine
import br.dev.s2w.kfoods.api.domain.model.Restaurant
import br.dev.s2w.kfoods.api.domain.repository.CuisineRepository
import br.dev.s2w.kfoods.api.domain.repository.RestaurantRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/test")
class TestController(
    private val cuisineRepository: CuisineRepository,
    private val restaurantRepository: RestaurantRepository
) {

    @GetMapping("/cuisines/by-name")
    fun findCuisinesByName(@RequestParam name: String): List<Cuisine> =
        cuisineRepository.findAllByNameContaining(name)

    @GetMapping("/cuisines/unique-by-name")
    fun findCuisineByName(@RequestParam name: String): Cuisine? =
        cuisineRepository.findByName(name)

    @GetMapping("/restaurants/by-delivery-fee")
    fun findRestaurantsByDeliveryFee(
        @RequestParam initialFee: BigDecimal,
        @RequestParam finalFee: BigDecimal
    ): List<Restaurant> =
        restaurantRepository.findByDeliveryFeeBetween(initialFee, finalFee)

    @GetMapping("/restaurants/by-name")
    fun findRestaurantsByName(@RequestParam name: String, cuisineId: Long): List<Restaurant> =
        restaurantRepository.findByNameContainingAndCuisineId(name, cuisineId)

}
