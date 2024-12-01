package br.dev.s2w.dependency.injection.notification

import br.dev.s2w.dependency.injection.model.Customer

class SmsNotifier : Notifier {

    override fun notify(customer: Customer, message: String) {
        println("Notifying ${customer.name} by SMS via phone number ${customer.phoneNumber}: $message")
    }
}