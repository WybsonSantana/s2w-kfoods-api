package br.dev.s2w.kfoods.api.configuration

import br.dev.s2w.kfoods.api.di.notification.Notifier
import br.dev.s2w.kfoods.api.di.service.CustomerActivationService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceConfig {

    @Bean
    fun customerActivationService(notifier: Notifier): CustomerActivationService {
        return CustomerActivationService(notifier)
    }
}