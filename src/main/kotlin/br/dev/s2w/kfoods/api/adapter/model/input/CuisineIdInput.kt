package br.dev.s2w.kfoods.api.adapter.model.input

import javax.validation.constraints.NotNull

data class CuisineIdInput(
    @NotNull
    val id: Long? = null
)