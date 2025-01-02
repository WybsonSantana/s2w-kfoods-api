package br.dev.s2w.kfoods.api.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime
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

    @Embedded
    @JsonIgnore
    var address: Address? = null,

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    @JsonIgnore
    var registrationDate: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    @JsonIgnore
    var lastUpdateDate: LocalDateTime? = null,

    @ManyToMany
    @JoinTable(
        name = "restaurant_payment_method",
        joinColumns = [JoinColumn(name = "restaurant_id")],
        inverseJoinColumns = [JoinColumn(name = "payment_method_id")]
    )
    @JsonIgnore
    var paymentMethods: MutableList<PaymentMethod> = mutableListOf()
)