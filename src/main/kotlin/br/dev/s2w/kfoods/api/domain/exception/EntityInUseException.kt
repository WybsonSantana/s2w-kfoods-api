package br.dev.s2w.kfoods.api.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
open class EntityInUseException(
    override val message: String?
    ) : BusinessException(message)