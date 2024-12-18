package br.dev.s2w.kfoods.api.di.notification

import br.dev.s2w.kfoods.api.di.model.Customer
import org.springframework.stereotype.Component

@Component
//@Primary
class EmailNotifier : Notifier {

    override fun notify(customer: Customer, message: String) {
        println("Notifying ${customer.name} by e-mail ${customer.email}: $message")
    }

}