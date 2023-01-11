package br.dev.s2w.kfoods.api.domain.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Restaurante(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    var nome: String = "",

    @Column(name = "taxa_frete", nullable = false)
    var taxaFrete: BigDecimal = BigDecimal.ZERO,

    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable = false)
    var cozinha: Cozinha

)