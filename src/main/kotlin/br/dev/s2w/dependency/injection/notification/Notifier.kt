package br.dev.s2w.dependency.injection.notification

import br.dev.s2w.dependency.injection.model.Customer

interface Notifier {
fun notify(customer: Customer, message: String)
}