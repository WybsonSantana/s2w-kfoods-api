package br.dev.s2w.kfoods.api.adapter.model.mixin

import br.dev.s2w.kfoods.api.domain.model.Address
import br.dev.s2w.kfoods.api.domain.model.Cuisine
import br.dev.s2w.kfoods.api.domain.model.PaymentMethod
import br.dev.s2w.kfoods.api.domain.model.Product
import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.OffsetDateTime

abstract class RestaurantMixin(
    //@JsonIgnoreProperties(value = ["name"], allowGetters = true)
    // The annotation @JsonIgnoreProperties has no cascade effect on Kotlin
    val cuisine: Cuisine? = null,

    @JsonIgnore
    val address: Address? = null,

    @JsonIgnore
    val registrationDate: OffsetDateTime? = null,

    @JsonIgnore
    val lastUpdateDate: OffsetDateTime? = null,

    @JsonIgnore
    val paymentMethods: MutableList<PaymentMethod> = mutableListOf(),

    @JsonIgnore
    val products: MutableList<Product> = mutableListOf()
)