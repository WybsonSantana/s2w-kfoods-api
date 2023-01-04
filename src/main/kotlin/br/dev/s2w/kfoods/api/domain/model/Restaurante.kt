package br.dev.s2w.kfoods.api.domain.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Restaurante(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val nome: String? = null,

    @Column(name = "taxa_frete")
    val taxaFrete: BigDecimal? = null,

    @ManyToOne
    val cozinha: Cozinha? = null

)