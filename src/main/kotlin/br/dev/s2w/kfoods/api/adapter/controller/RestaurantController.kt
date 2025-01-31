package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.core.validation.Groups
import br.dev.s2w.kfoods.api.domain.exception.BusinessException
import br.dev.s2w.kfoods.api.domain.exception.CuisineNotFoundException
import br.dev.s2w.kfoods.api.domain.model.Restaurant
import br.dev.s2w.kfoods.api.domain.repository.RestaurantRepository
import br.dev.s2w.kfoods.api.domain.service.RestaurantRegisterService
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
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
    fun add(@RequestBody @Validated(Groups.RestaurantRegistration::class) restaurant: Restaurant): Restaurant =
        try {
            restaurantRegister.save(restaurant)
        } catch (e: CuisineNotFoundException) {
            throw BusinessException(e.message, e)
        }

    @PutMapping("/{restaurantId}")
    fun update(
        @PathVariable restaurantId: Long,
        @RequestBody @Validated(Groups.RestaurantRegistration::class) restaurant: Restaurant
    ): Restaurant =
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

}
