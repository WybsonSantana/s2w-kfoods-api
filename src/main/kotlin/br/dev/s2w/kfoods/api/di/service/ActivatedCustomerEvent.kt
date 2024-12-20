package br.dev.s2w.kfoods.api.di.service

import br.dev.s2w.kfoods.api.di.model.Customer

data class ActivatedCustomerEvent(
    val customer: Customer
)