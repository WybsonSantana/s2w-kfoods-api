package br.dev.s2w.kfoods.api.adapter.assembler

import br.dev.s2w.kfoods.api.adapter.model.input.RestaurantInput
import br.dev.s2w.kfoods.api.domain.model.Cuisine
import br.dev.s2w.kfoods.api.domain.model.Restaurant

fun RestaurantInput.toDomainObject(): Restaurant =
    Restaurant(
        name = this.name,
        deliveryFee = this.deliveryFee,
        cuisine = Cuisine(
            id = this.cuisine?.id
        )
    )