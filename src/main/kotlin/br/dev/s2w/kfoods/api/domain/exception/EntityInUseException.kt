package br.dev.s2w.kfoods.api.domain.exception

data class EntityInUseException(
    override val message: String?
) : RuntimeException(message)