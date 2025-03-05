package br.dev.s2w.kfoods.api.adapter.controller

import br.dev.s2w.kfoods.api.adapter.model.RestaurantModel
import br.dev.s2w.kfoods.api.adapter.model.input.RestaurantInput
import br.dev.s2w.kfoods.api.adapter.model.input.toDomainObject
import br.dev.s2w.kfoods.api.adapter.model.toCollectionModel
import br.dev.s2w.kfoods.api.adapter.model.toModel
import br.dev.s2w.kfoods.api.domain.exception.BusinessException
import br.dev.s2w.kfoods.api.domain.exception.CuisineNotFoundException
import br.dev.s2w.kfoods.api.domain.repository.RestaurantRepository
import br.dev.s2w.kfoods.api.domain.service.RestaurantRegisterService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/restaurants")
class RestaurantController(
    private val restaurantRepository: RestaurantRepository,
    private val restaurantRegister: RestaurantRegisterService
) {

    @GetMapping
    fun list(): List<RestaurantModel> =
        restaurantRepository.findAll().toCollectionModel()

    @GetMapping("/{restaurantId}")
    fun find(@PathVariable restaurantId: Long): RestaurantModel =
        restaurantRegister.find(restaurantId).toModel()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun add(@RequestBody @Valid restaurantInput: RestaurantInput): RestaurantModel =
        try {
            val restaurant = restaurantInput.toDomainObject()
            restaurantRegister.save(restaurant).toModel()
        } catch (e: CuisineNotFoundException) {
            throw BusinessException(e.message, e)
        }

    @PutMapping("/{restaurantId}")
    fun update(
        @PathVariable restaurantId: Long,
        @RequestBody @Valid restaurantInput: RestaurantInput
    ): RestaurantModel =
        try {
            restaurantRegister.find(restaurantId).let { currentRestaurant ->
                restaurantInput.toDomainObject().let { restaurant ->
                    currentRestaurant.copy(
                        name = restaurant.name,
                        deliveryFee = restaurant.deliveryFee,
                        cuisine = restaurant.cuisine
                    )
                }.let { updatedRestaurant ->
                    restaurantRegister.save(updatedRestaurant).toModel()
                }
            }
        } catch (e: CuisineNotFoundException) {
            throw BusinessException(e.message, e)
        }

}
