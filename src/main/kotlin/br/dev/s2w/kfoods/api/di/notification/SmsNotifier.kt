package br.dev.s2w.kfoods.api.di.notification

import br.dev.s2w.kfoods.api.di.model.Customer
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
@Primary
class SmsNotifier : Notifier {

    override fun notify(customer: Customer, message: String) {
        println("Notifying ${customer.name} via SMS by phone number ${customer.phoneNumber}: $message")
    }
}