package br.dev.s2w.kfoods.api.di.service

import br.dev.s2w.kfoods.api.di.model.Customer
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class CustomerActivationService(
    private val eventPublisher: ApplicationEventPublisher
) {

    fun activate(customer: Customer) {
        customer.activate()

        eventPublisher.publishEvent(ActivatedCustomerEvent(customer))
    }
}