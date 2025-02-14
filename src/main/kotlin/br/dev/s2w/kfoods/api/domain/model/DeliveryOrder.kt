package br.dev.s2w.kfoods.api.domain.model

import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
data class DeliveryOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val subtotal: BigDecimal? = null,

    val deliveryFee: BigDecimal? = null,

    val totalAmount: BigDecimal? = null,

    @Embedded
    val deliveryAddress: Address? = null,

    val status: OrderStatus? = null,

    @CreationTimestamp
    val registrationDate: OffsetDateTime? = null,

    val confirmationDate: OffsetDateTime? = null,

    val cancellationDate: OffsetDateTime? = null,

    val deliveryDate: OffsetDateTime? = null,

    @ManyToOne
    @JoinColumn(nullable = false)
    val paymentMethod: PaymentMethod? = null,

    @ManyToOne
    @JoinColumn(nullable = false)
    val restaurant: Restaurant? = null,

    @ManyToOne
    @JoinColumn(nullable = false)
    val customer: Identity? = null,

    @OneToMany(mappedBy = "deliveryOrder")
    val items: MutableList<OrderItem> = mutableListOf()
)