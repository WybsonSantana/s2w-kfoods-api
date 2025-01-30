package br.dev.s2w.kfoods.api.core.validation

import org.springframework.validation.BindingResult

data class ValidationException(
    private val bindingResult: BindingResult
) : RuntimeException()