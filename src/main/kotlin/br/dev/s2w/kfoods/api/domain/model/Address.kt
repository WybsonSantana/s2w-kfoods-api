package br.dev.s2w.kfoods.api.domain.model

import javax.persistence.*

@Embeddable
data class Address(
    @Column(name = "address_postal_code")
    val postalCode: String? = null,

    @Column(name = "address_street")
    val street: String? = null,

    @Column(name = "address_number")
    val number: String? = null,

    @Column(name = "address_complement")
    val complement: String? = null,

    @Column(name = "address_district")
    val district: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_city_id")
    val city: City? = null
)