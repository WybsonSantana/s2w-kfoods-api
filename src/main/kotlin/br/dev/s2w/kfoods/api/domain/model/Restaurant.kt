package br.dev.s2w.kfoods.api.domain.model

import br.dev.s2w.kfoods.api.core.validation.FreeDeliveryFeeIncludesDescription
import br.dev.s2w.kfoods.api.core.validation.Groups.RestaurantRegistration
import br.dev.s2w.kfoods.api.core.validation.Multiple
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.PositiveOrZero
import javax.validation.groups.Default

@FreeDeliveryFeeIncludesDescription(
    groups = [Default::class, RestaurantRegistration::class],
    fieldValue = "deliveryFee",
    fieldDescription = "name",
    requiredDescription = "Frete Grátis"
)
@Entity
data class Restaurant(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @field:NotBlank(groups = [Default::class, RestaurantRegistration::class], message = "Name is required")
    @Column(nullable = false)
    var name: String? = null,

    @field:NotNull(groups = [Default::class, RestaurantRegistration::class])
    @field:PositiveOrZero(groups = [Default::class, RestaurantRegistration::class])
    @field:Multiple(groups = [Default::class, RestaurantRegistration::class], number = 5)
    @Column(name = "delivery_fee", nullable = false)
    var deliveryFee: BigDecimal? = null,

    @field:Valid
    @field:NotNull(groups = [Default::class, RestaurantRegistration::class])
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
    var paymentMethods: MutableList<PaymentMethod> = mutableListOf(),

    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore
    var products: MutableList<Product> = mutableListOf()
)