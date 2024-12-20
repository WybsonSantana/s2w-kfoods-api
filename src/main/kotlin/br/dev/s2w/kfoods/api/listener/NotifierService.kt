package br.dev.s2w.kfoods.api.listener

import br.dev.s2w.kfoods.api.di.notification.Notifier
import br.dev.s2w.kfoods.api.di.notification.NotifierType
import br.dev.s2w.kfoods.api.di.notification.UrgencyLevel
import br.dev.s2w.kfoods.api.di.service.ActivatedCustomerEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class NotifierService(
    @NotifierType(UrgencyLevel.NO_URGENCY)
    private val notifier: Notifier
) {

    @EventListener
    fun activatedCustomerListener(event: ActivatedCustomerEvent) {
        notifier.notify(event.customer, "Your registration in the system is active!")
    }
}