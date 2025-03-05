package br.dev.s2w.kfoods.api.adapter.model.input

import br.dev.s2w.kfoods.api.domain.model.Cuisine
import br.dev.s2w.kfoods.api.domain.model.Restaurant
import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.PositiveOrZero

data class RestaurantInput(
    @NotBlank
    val name: String? = null,

    @NotNull
    @PositiveOrZero
    val deliveryFee: BigDecimal? = null,

    @Valid
    @NotNull
    val cuisine: CuisineIdInput? = null
)

fun RestaurantInput.toDomainObject(): Restaurant = Restaurant(
    name = this.name,
    deliveryFee = this.deliveryFee,
    cuisine = Cuisine(
        id = this.cuisine?.id
    )
)
