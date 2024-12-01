package br.dev.s2w.dependency.injection.model

import java.math.BigDecimal

data class Product(
    val name: String,
    val totalValue: BigDecimal
)