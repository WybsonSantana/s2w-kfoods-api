package br.dev.s2w.kfoods.api.adapter.assembler

import br.dev.s2w.kfoods.api.adapter.model.CuisineModel
import br.dev.s2w.kfoods.api.adapter.model.RestaurantModel
import br.dev.s2w.kfoods.api.domain.model.Restaurant

fun Restaurant.toModel() =
    RestaurantModel(
        id = this.id,
        name = this.name,
        deliveryFee = this.deliveryFee,
        cuisine = CuisineModel(
            id = this.cuisine?.id,
            name = this.cuisine?.name
        )
    )

fun List<Restaurant>.toCollectionModel() =
    this.map { it.toModel() }