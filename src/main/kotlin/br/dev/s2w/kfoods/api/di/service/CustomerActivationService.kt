package br.dev.s2w.kfoods.api.di.service

import br.dev.s2w.kfoods.api.di.model.Customer
import br.dev.s2w.kfoods.api.di.notification.Notifier
import br.dev.s2w.kfoods.api.di.notification.NotifierType
import br.dev.s2w.kfoods.api.di.notification.UrgencyLevel
import org.springframework.stereotype.Component

class CustomerActivationService(
    private val notifier: Notifier
) {

    fun init() = println("INIT $notifier")

    fun destroy() = println("DESTROY")

    fun activate(customer: Customer) {
        customer.activate()

        notifier.notify(customer, "Your registration in the system is active!")
    }
}