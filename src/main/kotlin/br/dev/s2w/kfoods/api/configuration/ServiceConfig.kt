package br.dev.s2w.kfoods.api.configuration

import br.dev.s2w.kfoods.api.di.notification.Notifier
import br.dev.s2w.kfoods.api.di.notification.NotifierType
import br.dev.s2w.kfoods.api.di.notification.UrgencyLevel
import br.dev.s2w.kfoods.api.di.service.CustomerActivationService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    fun customerActivationService(@NotifierType(UrgencyLevel.NO_URGENCY) notifier: Notifier) = CustomerActivationService(notifier)
}