package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.domain.exception.EntityNotFoundException
import br.dev.s2w.kfoods.api.domain.model.Restaurant
import br.dev.s2w.kfoods.api.domain.repository.RestaurantRepository
import br.dev.s2w.kfoods.api.domain.service.RestaurantRegisterService
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/restaurants")
class RestaurantController(
    private val restaurantRepository: RestaurantRepository,
    private val restaurantRegister: RestaurantRegisterService
) {

    @GetMapping
    fun list(): List<Restaurant> =
        restaurantRepository.list()

    @GetMapping("/{restaurantId}")
    fun search(@PathVariable restaurantId: Long): ResponseEntity<Restaurant> {
        val restaurant = restaurantRepository.search(restaurantId)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(restaurant)
    }

    @PostMapping
    fun add(@RequestBody restaurant: Restaurant): ResponseEntity<Any> {
        try {
            restaurantRegister.save(restaurant).also {
                return ResponseEntity.status(HttpStatus.CREATED).body(it)
            }
        } catch (e: EntityNotFoundException) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    @PutMapping("/{restaurantId}")
    fun update(@PathVariable restaurantId: Long, @RequestBody restaurant: Restaurant): ResponseEntity<Any> {
        try {
            val currentRestaurant = restaurantRepository.search(restaurantId)
                ?: return ResponseEntity.notFound().build()

            BeanUtils.copyProperties(restaurant, currentRestaurant, "id")

            restaurantRegister.save(currentRestaurant).also {
                return ResponseEntity.ok(it)
            }
        } catch (e: EntityNotFoundException) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }
}