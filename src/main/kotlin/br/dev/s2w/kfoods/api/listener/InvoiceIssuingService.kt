package br.dev.s2w.kfoods.api.listener

import br.dev.s2w.kfoods.api.di.service.ActivatedCustomerEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class InvoiceIssuingService {

    @EventListener
    fun activatedCustomerListener(event: ActivatedCustomerEvent) {
        println("Issuing invoice to the customer ${event.customer.name}...")
    }
}