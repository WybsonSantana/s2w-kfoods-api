package br.dev.s2w.kfoods.api.domain.exception

open class EntityInUseException(
    override val message: String?
) : BusinessException(message)