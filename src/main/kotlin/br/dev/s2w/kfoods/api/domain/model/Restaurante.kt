package br.dev.s2w.kfoods.api.domain.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Restaurante(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long,

    private val nome: String,

    @Column(name = "taxa_frete")
    private val taxaFrete: BigDecimal

)