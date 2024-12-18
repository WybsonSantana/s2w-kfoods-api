package br.dev.s2w.kfoods.api.di.service

import br.dev.s2w.kfoods.api.di.model.Customer
import br.dev.s2w.kfoods.api.di.notification.Notifier
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CustomerActivationService {

    @Autowired(required = false)
    private val notifier: Notifier? = null

    fun activate(customer: Customer) {
        customer.activate()

        notifier?.notify(customer, "Your registration in the system is active!")
            ?: println("There is no notifier, but the client has been activated.")
    }
}