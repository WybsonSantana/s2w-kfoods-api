package br.dev.s2w.kfoods.api.adapter.model.mixin

import br.dev.s2w.kfoods.api.domain.model.Restaurant
import com.fasterxml.jackson.annotation.JsonIgnore

abstract class CuisineMixin(
    @JsonIgnore
    val restaurants: MutableList<Restaurant> = mutableListOf()

)