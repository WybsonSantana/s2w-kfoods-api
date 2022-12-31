package br.dev.s2w.kfoods.api.domain.model

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Restaurante(
    @Id
    private val id: Long,

    private val nome: String,

    @Column(name = "taxa_frete")
    private val taxaFrete: BigDecimal

)