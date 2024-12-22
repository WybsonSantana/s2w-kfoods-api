package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.PaymentMethod

interface PaymentMethodRepository {
    fun list(): List<PaymentMethod>
    fun search(id: Long): PaymentMethod
    fun save(paymentMethod: PaymentMethod): PaymentMethod
    fun remove(paymentMethod: PaymentMethod)
}