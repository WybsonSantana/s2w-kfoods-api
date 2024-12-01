package br.dev.s2w.dependency.injection.notification

import br.dev.s2w.dependency.injection.model.Customer

class EmailNotifier : Notifier {

    override fun notify(customer: Customer, message: String) {
        println("Notifying ${customer.name} by e-mail ${customer.email}: $message")
    }
}