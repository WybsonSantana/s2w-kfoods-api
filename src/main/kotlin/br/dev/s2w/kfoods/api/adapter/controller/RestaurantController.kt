package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.domain.exception.BusinessException
import br.dev.s2w.kfoods.api.domain.exception.EntityNotFoundException
import br.dev.s2w.kfoods.api.domain.model.Restaurant
import br.dev.s2w.kfoods.api.domain.repository.RestaurantRepository
import br.dev.s2w.kfoods.api.domain.service.RestaurantRegisterService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
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
    fun find(@PathVariable restaurantId: Long): Restaurant =
        restaurantRegister.find(restaurantId)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun add(@RequestBody restaurant: Restaurant): Restaurant =
        try {
            restaurantRegister.save(restaurant)
        } catch (e: EntityNotFoundException) {
            throw BusinessException(e.message)
        }

    @PutMapping("/{restaurantId}")
    fun update(@PathVariable restaurantId: Long, @RequestBody restaurant: Restaurant): Restaurant =
        restaurantRegister.find(restaurantId).also {
            BeanUtils.copyProperties(
                restaurant, it,
                "id", "paymentMethods", "address", "registrationDate", "products"
            )

            try {
                restaurantRegister.save(it)
            } catch (e: EntityNotFoundException) {
                throw BusinessException(e.message)
            }
        }

    @PatchMapping("/{restaurantId}")
    fun patch(@PathVariable restaurantId: Long, @RequestBody fields: Map<String, Any>): Restaurant =
        restaurantRegister.find(restaurantId).also {
            merge(fields, it)
            update(restaurantId, it)
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
