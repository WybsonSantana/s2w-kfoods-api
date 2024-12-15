package br.dev.s2w.kfoods.api.configuration

import br.dev.s2w.kfoods.api.di.notification.EmailNotifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NotificationConfig {

    @Bean
    fun emailNotifier(): EmailNotifier {
        val notifier = EmailNotifier("smtp.s2w.dev.br")
        notifier.uppercase = true

        return notifier
    }
}