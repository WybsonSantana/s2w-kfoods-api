package br.dev.s2w.kfoods.api.domain.exception

data class EntityNotFoundException(
    override val message: String?
) : RuntimeException(message)