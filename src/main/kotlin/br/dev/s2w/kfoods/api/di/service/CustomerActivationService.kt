package br.dev.s2w.kfoods.api.di.service

import br.dev.s2w.kfoods.api.di.model.Customer
import br.dev.s2w.kfoods.api.di.notification.Notifier

class CustomerActivationService(
    private val notifier: Notifier
) {

    init {
        println("CustomerActivationService: $notifier")
    }

    fun activate(customer: Customer) {
        customer.activate()
        notifier.notify(customer, "Your registration in the system is active!")
    }
}