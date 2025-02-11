package br.dev.s2w.kfoods.api.domain.model

import br.dev.s2w.kfoods.api.core.validation.Groups.RestaurantRegistration
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.PositiveOrZero

@Entity
data class Restaurant(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:NotBlank(groups = [RestaurantRegistration::class])
    @Column(nullable = false)
    val name: String? = null,

    @field:NotNull(groups = [RestaurantRegistration::class])
    @field:PositiveOrZero(groups = [RestaurantRegistration::class])
    @Column(name = "delivery_fee", nullable = false)
    val deliveryFee: BigDecimal? = null,

    @field:Valid
    @field:NotNull(groups = [RestaurantRegistration::class])
    @ManyToOne
    @JoinColumn(name = "cuisine_id", nullable = false)
    val cuisine: Cuisine? = null,

    @Embedded
    val address: Address? = null,

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    val registrationDate: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    val lastUpdateDate: LocalDateTime? = null,

    @ManyToMany
    @JoinTable(
        name = "restaurant_payment_method",
        joinColumns = [JoinColumn(name = "restaurant_id")],
        inverseJoinColumns = [JoinColumn(name = "payment_method_id")]
    )
    val paymentMethods: MutableList<PaymentMethod> = mutableListOf(),

    @OneToMany(mappedBy = "restaurant")
    val products: MutableList<Product> = mutableListOf()
)