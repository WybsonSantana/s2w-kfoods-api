package br.dev.s2w.kfoods.api.domain.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String? = null,

    @Column(nullable = false)
    var description: String? = null,

    @Column(nullable = false)
    var price: BigDecimal? = null,

    @Column(nullable = false)
    var active: Boolean? = null,

    @ManyToOne
    @JoinColumn(nullable = false)
    var restaurant: Restaurant? = null
)