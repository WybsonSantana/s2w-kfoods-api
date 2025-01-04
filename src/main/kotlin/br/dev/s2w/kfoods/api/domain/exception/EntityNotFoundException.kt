package br.dev.s2w.kfoods.api.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

//@ResponseStatus(HttpStatus.NOT_FOUND)
data class EntityNotFoundException(
    private val status: HttpStatus = HttpStatus.NOT_FOUND,
    override val message: String
) : ResponseStatusException(status, message) {

    constructor(message: String) : this(HttpStatus.NOT_FOUND, message)

}
