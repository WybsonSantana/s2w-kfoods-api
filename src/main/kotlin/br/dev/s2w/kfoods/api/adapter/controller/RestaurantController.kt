package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.domain.exception.EntityNotFoundException
import br.dev.s2w.kfoods.api.domain.model.Restaurant
import br.dev.s2w.kfoods.api.domain.repository.RestaurantRepository
import br.dev.s2w.kfoods.api.domain.service.RestaurantRegisterService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
    fun add(@RequestBody restaurant: Restaurant): ResponseEntity<*> {
        try {
            restaurantRegister.save(restaurant).also {
                return ResponseEntity.status(HttpStatus.CREATED).body(it)
            }
        } catch (e: EntityNotFoundException) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }
}