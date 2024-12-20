package br.dev.s2w.kfoods.api.di.notification

import br.dev.s2w.kfoods.api.di.model.Customer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
@NotifierType(UrgencyLevel.NO_URGENCY)
class EmailNotifier : Notifier {

    @Value("\${notifier.email.server-host}")
    private val host: String? = null

    @Value("\${notifier.email.server-port}")
    private val port: Int? = null

    override fun notify(customer: Customer, message: String) {
        println("Server host: $host")
        println("Server port: $port")

        println("Notifying ${customer.name} by e-mail ${customer.email}: $message")
    }

}