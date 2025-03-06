package br.dev.s2w.kfoods.api.adapter.model

import br.dev.s2w.kfoods.api.domain.model.Restaurant
import java.math.BigDecimal

data class RestaurantModel(
    val id: Long? = null,
    val name: String? = null,
    val deliveryFee: BigDecimal? = null,
    val cuisine: CuisineModel? = null
)