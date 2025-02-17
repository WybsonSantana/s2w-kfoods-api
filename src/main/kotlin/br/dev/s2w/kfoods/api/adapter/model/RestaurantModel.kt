package br.dev.s2w.kfoods.api.adapter.model

import br.dev.s2w.kfoods.api.domain.model.Restaurant
import java.math.BigDecimal

data class RestaurantModel(
    val id: Long? = null,
    val name: String? = null,
    val deliveryFee: BigDecimal? = null,
    val cuisine: CuisineModel? = null
)

fun Restaurant.toModel() = RestaurantModel(
    id = this.id,
    name = this.name,
    deliveryFee = this.deliveryFee,
    cuisine = CuisineModel(
        id = this.cuisine?.id,
        name = this.cuisine?.name
    )
)

fun List<Restaurant>.toCollectionModel() = this.map { it.toModel() }
