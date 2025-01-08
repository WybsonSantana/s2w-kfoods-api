package br.dev.s2w.kfoods.api.domain.exception

abstract class EntityNotFoundException(
    override val message: String?
) : BusinessException(message)