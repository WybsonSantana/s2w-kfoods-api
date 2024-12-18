package br.dev.s2w.kfoods.api.di.service

import br.dev.s2w.kfoods.api.di.model.Customer
import br.dev.s2w.kfoods.api.di.notification.Notifier
import br.dev.s2w.kfoods.api.di.notification.NotifierType
import br.dev.s2w.kfoods.api.di.notification.UrgencyLevel
import org.springframework.stereotype.Component

@Component
class CustomerActivationService(
    @NotifierType(UrgencyLevel.URGENT)
    private val notifier: Notifier
) {

    fun activate(customer: Customer) {
        customer.activate()

        notifier.notify(customer, "Your registration in the system is active!")
    }
}