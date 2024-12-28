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

    @GetMapping("/cuisines/exists")
    fun cuisineExists(@RequestParam name: String): Boolean =
        cuisineRepository.existsByName(name)

    @GetMapping("/restaurants/by-delivery-fee")
    fun findRestaurantsByDeliveryFee(
        @RequestParam initialFee: BigDecimal,
        @RequestParam finalFee: BigDecimal
    ): List<Restaurant> =
        restaurantRepository.findByDeliveryFeeBetween(initialFee, finalFee)

    @GetMapping("/restaurants/by-name")
    fun findRestaurantsByName(@RequestParam name: String, cuisineId: Long): List<Restaurant> =
        restaurantRepository.queryByName(name, cuisineId)

    @GetMapping("/restaurants/first-by-name")
    fun findFirstRestaurantByName(@RequestParam name: String): Restaurant? =
        restaurantRepository.getFirstRestaurantByNameContaining(name)

    @GetMapping("/restaurants/top-two-by-name")
    fun findTopTwoRestaurantsByName(@RequestParam name: String): List<Restaurant> =
        restaurantRepository.queryTop2ByNameContaining(name)

    @GetMapping("/restaurants/by-name-and-delivery-fee")
    fun findRestaurantsByNamAndDeliveryFee(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) initialFee: BigDecimal?,
        @RequestParam(required = false) finalFee: BigDecimal?
    ): List<Restaurant> =
        restaurantRepository.find(name, initialFee, finalFee)

    @GetMapping("/restaurants/count-by-cuisine")
    fun countRestaurantsByCuisine(@RequestParam cuisineId: Long): Int =
        restaurantRepository.countByCuisineId(cuisineId)

}
