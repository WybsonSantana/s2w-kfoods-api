package br.dev.s2w.kfoods.api.di.service

import br.dev.s2w.kfoods.api.di.model.Customer
import br.dev.s2w.kfoods.api.di.notification.Notifier
import org.springframework.stereotype.Component

@Component
class CustomerActivationService(
    private val notifiers: List<Notifier>
) {

    fun activate(customer: Customer) {
        customer.activate()

        for (notifier in notifiers)
            notifier.notify(customer, "Your registration in the system is active!")
    }
}