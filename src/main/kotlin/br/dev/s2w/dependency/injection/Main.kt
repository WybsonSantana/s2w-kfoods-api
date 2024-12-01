package br.dev.s2w.dependency.injection

import br.dev.s2w.dependency.injection.model.Customer
import br.dev.s2w.dependency.injection.notification.EmailNotifier
import br.dev.s2w.dependency.injection.notification.SmsNotifier
import br.dev.s2w.dependency.injection.service.CustomerActivationService

fun main() {
    val johnDue = Customer("John Due", "john.due@s2w.com", "+55 11 99897-9695")
    val janeDue = Customer("Jane Due", "jane.due@s2w.com", "+55 11 98786-8584")

val notifier = EmailNotifier()
val customerActivation = CustomerActivationService(notifier)

    customerActivation.activate(johnDue)
    customerActivation.activate(janeDue)
}