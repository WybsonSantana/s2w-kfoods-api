package br.dev.s2w.kfoods.api.domain.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Restaurant(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String? = null,

    @Column(name = "delivery_fee", nullable = false)
    var deliveryFee: BigDecimal? = null,

    @ManyToOne
    @JoinColumn(name = "cuisine_id", nullable = false)
    var cuisine: Cuisine? = null,

    @ManyToMany
    @JoinTable(
        name = "restaurant_payment_method",
        joinColumns = [JoinColumn(name = "restaurant_id")],
        inverseJoinColumns = [JoinColumn(name = "payment_method_id")]
    )
    var paymentMethods: MutableList<PaymentMethod> = mutableListOf()
)