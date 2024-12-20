package br.dev.s2w.kfoods.api.di.notification

import br.dev.s2w.kfoods.api.di.model.Customer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
@NotifierType(UrgencyLevel.NO_URGENCY)
class EmailNotifier(
    private val properties: NotifierProperties
) : Notifier {

    override fun notify(customer: Customer, message: String) {
        println("Server host: ${properties.serverHost}")
        println("Server port: ${properties.serverPort}")

        println("Notifying ${customer.name} by e-mail ${customer.email}: $message")
    }

}