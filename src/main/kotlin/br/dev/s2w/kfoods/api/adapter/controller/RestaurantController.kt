package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.domain.exception.EntityNotFoundException
import br.dev.s2w.kfoods.api.domain.model.Restaurant
import br.dev.s2w.kfoods.api.domain.repository.RestaurantRepository
import br.dev.s2w.kfoods.api.domain.service.RestaurantRegisterService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.ReflectionUtils
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/restaurants")
class RestaurantController(
    private val restaurantRepository: RestaurantRepository,
    private val restaurantRegister: RestaurantRegisterService
) {

    @GetMapping
    fun list(): List<Restaurant> =
        restaurantRepository.findAll()

    @GetMapping("/{restaurantId}")
    fun search(@PathVariable restaurantId: Long): ResponseEntity<Restaurant> {
        val restaurant = restaurantRepository.findById(restaurantId).orElse(null)
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
            val currentRestaurant = restaurantRepository.findById(restaurantId).orElse(null)
                ?: return ResponseEntity.notFound().build()

            BeanUtils.copyProperties(
                restaurant, currentRestaurant,
                "id", "paymentMethods", "address", "registrationDate", "products"
            )

            restaurantRegister.save(currentRestaurant).also {
                return ResponseEntity.ok(it)
            }
        } catch (e: EntityNotFoundException) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    @PatchMapping("/{restaurantId}")
    fun patch(@PathVariable restaurantId: Long, @RequestBody fields: Map<String, Any>): ResponseEntity<Any> {
        val currentRestaurant = restaurantRepository.findById(restaurantId).orElse(null)
            ?: return ResponseEntity.notFound().build()

        merge(fields, currentRestaurant)

        return update(restaurantId, currentRestaurant)

    }

    private fun merge(fields: Map<String, Any>, targetRestaurant: Restaurant) {
        val mapper = ObjectMapper()
        val currentRestaurant = mapper.convertValue(fields, Restaurant::class.java)

        fields.forEach { (key, value) ->
            val field = ReflectionUtils.findField(Restaurant::class.java, key)
            field?.isAccessible = true

            ReflectionUtils.getField(field!!, currentRestaurant).also {
                ReflectionUtils.setField(field, targetRestaurant, it)
            }
        }
    }


}
