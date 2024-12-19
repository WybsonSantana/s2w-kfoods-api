package br.dev.s2w.kfoods.api.di.notification

import br.dev.s2w.kfoods.api.di.model.Customer
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@NotifierType(UrgencyLevel.NO_URGENCY)
@Profile("dev")
class EmailMockNotifier : Notifier {

    init {
        println("Mock EmailNotifier")
    }

    override fun notify(customer: Customer, message: String) {
        println("Notifying ${customer.name} by e-mail ${customer.email}: $message")
    }

}