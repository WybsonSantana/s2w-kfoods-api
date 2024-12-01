package br.dev.s2w.dependency.injection.service

import br.dev.s2w.dependency.injection.model.Customer
import br.dev.s2w.dependency.injection.model.Product
import br.dev.s2w.dependency.injection.notification.Notifier

class InvoiceIssuanceService(
    private val notifier: Notifier
) {

    fun issue(customer: Customer, product: Product) {
        notifier.notify(customer, "Invoice for product ${product.name} issued successfully!")
    }
}