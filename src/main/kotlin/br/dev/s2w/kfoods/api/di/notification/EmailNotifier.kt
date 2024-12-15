package br.dev.s2w.kfoods.api.di.notification

import br.dev.s2w.kfoods.api.di.model.Customer

class EmailNotifier(
    private val hostSmtpServer: String
) : Notifier {

    var uppercase: Boolean = false

    init {
        println("EmailNotifier")
    }

    override fun notify(customer: Customer, message: String) {
        val notificationMessage = if (uppercase) message.uppercase() else message

        println("Notifying ${customer.name} by e-mail ${customer.email}: $notificationMessage")
    }

}