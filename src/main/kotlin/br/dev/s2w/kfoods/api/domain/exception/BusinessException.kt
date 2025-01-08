package br.dev.s2w.kfoods.api.domain.exception

open class BusinessException(
    override val message: String?,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)