package br.dev.s2w.kfoods.api.domain.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Restaurante(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val nome: String,

    @Column(name = "taxa_frete", nullable = false)
    val taxaFrete: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "cozinha_id")
    val cozinha: Cozinha? = null

)