package br.dev.s2w.kfoods.api.domain.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var unitPrice: BigDecimal? = null,

    var totalPrice: BigDecimal? = null,

    var quantity: Int? = null,

    var note: String? = null,

    @ManyToOne
    @JoinColumn(nullable = false)
    var deliveryOrder: DeliveryOrder? = null,

    @ManyToOne
    @JoinColumn(nullable = false)
    var product: Product? = null,
    )