package br.dev.s2w.kfoods.api.core.validation

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

@Configuration
class ValidationConfig {

    @Bean
    fun validatorFactoryBean(messageSource: MessageSource) = LocalValidatorFactoryBean().also {
        it.setValidationMessageSource(messageSource)
        return it
    }

}
