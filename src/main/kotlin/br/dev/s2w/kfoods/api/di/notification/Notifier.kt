package br.dev.s2w.kfoods.api.di.notification

import br.dev.s2w.kfoods.api.di.model.Customer

interface Notifier {
    fun notify(customer: Customer, message: String)
}