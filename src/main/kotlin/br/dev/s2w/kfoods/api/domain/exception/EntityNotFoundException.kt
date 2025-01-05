package br.dev.s2w.kfoods.api.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
open class EntityNotFoundException(
    override val message: String?
) : BusinessException(message)