package br.dev.s2w.kfoods.api.domain.model

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Embeddable
data class Address(
    @Column(name = "address_postal_code")
    var postalCode: String? = null,

    @Column(name = "address_street")
    var street: String? = null,

    @Column(name = "address_number")
    var number: String? = null,

    @Column(name = "address_complement")
    var complement: String? = null,

    @Column(name = "address_district")
    var district: String? = null,

    @ManyToOne
    @JoinColumn(name = "address_city_id")
    var city: City? = null

)