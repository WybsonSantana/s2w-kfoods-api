package br.dev.s2w.kfoods.api.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
open class BusinessException(
    override val message: String?,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)