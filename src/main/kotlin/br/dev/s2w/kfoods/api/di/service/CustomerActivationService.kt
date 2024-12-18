package br.dev.s2w.kfoods.api.di.service

import br.dev.s2w.kfoods.api.di.model.Customer
import br.dev.s2w.kfoods.api.di.notification.Notifier
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class CustomerActivationService(
    @Qualifier("urgent")
    private val notifier: Notifier
) {

    fun activate(customer: Customer) {
        customer.activate()

        notifier.notify(customer, "Your registration in the system is active!")
    }
}