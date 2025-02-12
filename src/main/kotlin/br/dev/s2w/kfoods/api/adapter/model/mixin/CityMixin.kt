package br.dev.s2w.kfoods.api.adapter.model.mixin

import br.dev.s2w.kfoods.api.domain.model.State

abstract class CityMixin(
    //@JsonIgnoreProperties(value = ["name"], allowGetters = true)
    // The annotation @JsonIgnoreProperties has no cascade effect on Kotlin
    val state: State? = null
)