package br.dev.s2w.kfoods.api.di.service

import br.dev.s2w.kfoods.api.di.model.Customer
import br.dev.s2w.kfoods.api.di.notification.EmailNotifier
import org.springframework.stereotype.Component

@Component
class CustomerActivationService(
    private val notifier: EmailNotifier
) {

    fun activate(customer: Customer) {
        customer.activate()
        notifier.notify(customer, "Your registration in the system is active!")
    }
}