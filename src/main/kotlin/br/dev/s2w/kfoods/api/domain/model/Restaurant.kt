package br.dev.s2w.kfoods.api.domain.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Restaurant(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String? = null,

    @Column(name = "delivery_fee")
    var deliveryFee: BigDecimal? = null,

    @ManyToOne
    var cuisine: Cuisine? = null
)