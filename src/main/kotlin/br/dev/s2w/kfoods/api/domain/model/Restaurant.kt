package br.dev.s2w.kfoods.api.domain.model

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Restaurant(
    @Id
    var id: Long,

    var name: String,

    @Column(name = "delivery_fee")
    var deliveryFee: BigDecimal
)