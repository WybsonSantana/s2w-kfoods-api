package br.dev.s2w.kfoods.api.adapter.exceptionhandler

import br.dev.s2w.kfoods.api.domain.exception.BusinessException
import br.dev.s2w.kfoods.api.domain.exception.EntityInUseException
import br.dev.s2w.kfoods.api.domain.exception.EntityNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class AdapterExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleExceptionInternal(
        e: Exception,
        body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        lateinit var problem: Problem

        if (body == null) {
            problem = Problem(
                timestamp = LocalDateTime.now(),
                message = status.reasonPhrase
            )
        } else if (body is String) {
            problem = Problem(
                timestamp = LocalDateTime.now(),
                message = body
            )
        }

        return super.handleExceptionInternal(e, problem, headers, status, request)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException, request: WebRequest): ResponseEntity<Any> {
        val problem = e.message
        val headers = HttpHeaders()
        val status = HttpStatus.BAD_REQUEST

        return handleExceptionInternal(e, problem, headers, status, request)
    }

    @ExceptionHandler(EntityInUseException::class)
    fun handleEntityInUseException(e: EntityInUseException, request: WebRequest): ResponseEntity<Any> {
        val problem = e.message
        val headers = HttpHeaders()
        val status = HttpStatus.CONFLICT

        return handleExceptionInternal(e, problem, headers, status, request)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(e: EntityNotFoundException, request: WebRequest): ResponseEntity<Any> {
        val problem = e.message
        val headers = HttpHeaders()
        val status = HttpStatus.NOT_FOUND

        return handleExceptionInternal(e, problem, headers, status, request)
    }

}
