package br.dev.s2w.kfoods.api.domain.model

import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class DeliveryOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var subtotal: BigDecimal? = null,

    var deliveryFee: BigDecimal? = null,

    var totalAmount: BigDecimal? = null,

    @Embedded
    var deliveryAddress: Address? = null,

    var status: OrderStatus? = null,

    @CreationTimestamp
    var registrationDate: LocalDateTime? = null,

    var confirmationDate: LocalDateTime? = null,

    var cancellationDate: LocalDateTime? = null,

    var deliveryDate: LocalDateTime? = null,

    @ManyToOne
    @JoinColumn(nullable = false)
    var paymentMethod: PaymentMethod? = null,

    @ManyToOne
    @JoinColumn(nullable = false)
    var restaurant: Restaurant? = null,

    @ManyToOne
    @JoinColumn(nullable = false)
    var customer: Identity? = null,

    @OneToMany(mappedBy = "deliveryOrder")
    var items: MutableList<OrderItem> = mutableListOf()

)