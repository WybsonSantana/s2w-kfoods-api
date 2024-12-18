package br.dev.s2w.kfoods.api.di.service

import br.dev.s2w.kfoods.api.di.model.Customer
import br.dev.s2w.kfoods.api.di.notification.Notifier
import org.springframework.stereotype.Component

@Component
class CustomerActivationService(
    private val notifier: Notifier
) {

    //    @Autowired
//    private lateinit var notifier: Notifier

    fun activate(customer: Customer) {
        customer.activate()
        notifier.notify(customer, "Your registration in the system is active!")
    }

//    @Autowired
//    fun setNotifier(notifier: Notifier) {
//        this.notifier = notifier
//    }
}