package br.dev.s2w.kfoods.api.domain.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String? = null,

    @Column(nullable = false)
    val description: String? = null,

    @Column(nullable = false)
    val price: BigDecimal? = null,

    @Column(nullable = false)
    val active: Boolean? = null,

    @ManyToOne
    @JoinColumn(nullable = false)
    val restaurant: Restaurant? = null
)