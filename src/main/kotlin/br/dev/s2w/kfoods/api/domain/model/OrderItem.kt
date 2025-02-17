package br.dev.s2w.kfoods.api.domain.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val unitPrice: BigDecimal? = null,

    val totalPrice: BigDecimal? = null,

    val quantity: Int? = null,

    val note: String? = null,

    @ManyToOne
    @JoinColumn(nullable = false)
    val deliveryOrder: DeliveryOrder? = null,

    @ManyToOne
    @JoinColumn(nullable = false)
    val product: Product? = null,
    )