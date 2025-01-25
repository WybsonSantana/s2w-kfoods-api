package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.Groups
import br.dev.s2w.kfoods.api.domain.exception.BusinessException
import br.dev.s2w.kfoods.api.domain.exception.CuisineNotFoundException
import br.dev.s2w.kfoods.api.domain.model.Restaurant
import br.dev.s2w.kfoods.api.domain.repository.RestaurantRepository
import br.dev.s2w.kfoods.api.domain.service.RestaurantRegisterService
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.lang3.exception.ExceptionUtils
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.util.ReflectionUtils
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

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
    fun add(@RequestBody @Validated(Groups.RestaurantRegistration::class) restaurant: Restaurant): Restaurant =
        try {
            restaurantRegister.save(restaurant)
        } catch (e: CuisineNotFoundException) {
            throw BusinessException(e.message, e)
        }

    @PutMapping("/{restaurantId}")
    fun update(@PathVariable restaurantId: Long, @RequestBody restaurant: Restaurant): Restaurant =
        try {
            restaurantRegister.find(restaurantId).also {
                BeanUtils.copyProperties(
                    restaurant, it,
                    "id", "paymentMethods", "address", "registrationDate", "products"
                )

                restaurantRegister.save(it)
            }
        } catch (e: CuisineNotFoundException) {
            throw BusinessException(e.message, e)
        }

    @PatchMapping("/{restaurantId}")
    fun patch(
        @PathVariable restaurantId: Long,
        @RequestBody fields: Map<String, Any>,
        request: HttpServletRequest
    ): Restaurant =
        restaurantRegister.find(restaurantId).also {
            merge(fields, it, request)
            update(restaurantId, it)
        }

    private fun merge(fields: Map<String, Any>, targetRestaurant: Restaurant, request: HttpServletRequest) {
        val serverHttpRequest = ServletServerHttpRequest(request)

        try {
            val mapper = ObjectMapper()
            mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true)
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)

            val currentRestaurant = mapper.convertValue(fields, Restaurant::class.java)

            fields.forEach { (key, value) ->
                val field = ReflectionUtils.findField(Restaurant::class.java, key)
                field?.isAccessible = true

                ReflectionUtils.getField(field!!, currentRestaurant).also {
                    ReflectionUtils.setField(field, targetRestaurant, it)
                }
            }
        } catch (e: IllegalArgumentException) {
            val rootCause = ExceptionUtils.getRootCause(e)
            throw HttpMessageNotReadableException(e.message.toString(), rootCause, serverHttpRequest)
        }
    }

}
