package br.dev.s2w.kfoods.api.adapter.exceptionhandler

import br.dev.s2w.kfoods.api.domain.exception.BusinessException
import br.dev.s2w.kfoods.api.domain.exception.EntityInUseException
import br.dev.s2w.kfoods.api.domain.exception.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class AdapterExceptionHandler {

    @ExceptionHandler(BusinessException::class)
    fun handlerBusinessException(e: BusinessException): ResponseEntity<Any> {
        val problem = Problem(
            timestamp = LocalDateTime.now(),
            message = e.message.toString()
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem)
    }

    @ExceptionHandler(EntityInUseException::class)
    fun handlerEntityInUseException(e: EntityInUseException): ResponseEntity<Any> {
        val problem = Problem(
            timestamp = LocalDateTime.now(),
            message = e.message.toString()
        )

        return ResponseEntity.status(HttpStatus.CONFLICT).body(problem)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handlerEntityNotFoundException(e: EntityNotFoundException): ResponseEntity<Any> {
        val problem = Problem(
            timestamp = LocalDateTime.now(),
            message = e.message.toString()
        )

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem)
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException::class)
    fun handlerHttpMediaTypeNotSupportedException(): ResponseEntity<Any> {
        val problem = Problem(
            timestamp = LocalDateTime.now(),
            message = "Unsupported media type"
        )

        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(problem)
    }

}
