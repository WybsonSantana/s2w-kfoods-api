package br.dev.s2w.dependency.injection.service

import br.dev.s2w.dependency.injection.model.Customer
import br.dev.s2w.dependency.injection.notification.Notifier

class CustomerActivationService(
    private val notifier: Notifier
) {

    fun activate(customer: Customer) {
        customer.activate()
        notifier.notify(customer, "Your registration in the system is active!")
    }
}