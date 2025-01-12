package br.dev.s2w.kfoods.api.adapter.exceptionhandler

import br.dev.s2w.kfoods.api.domain.exception.BusinessException
import br.dev.s2w.kfoods.api.domain.exception.EntityInUseException
import br.dev.s2w.kfoods.api.domain.exception.EntityNotFoundException
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.fasterxml.jackson.databind.exc.PropertyBindingException
import org.apache.commons.lang3.exception.ExceptionUtils
import org.springframework.beans.TypeMismatchException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class AdapterExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleExceptionInternal(
        e: Exception,
        body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val problem = when (body) {
            null -> Problem(status = status.value(), title = status.reasonPhrase)
            is String -> Problem(status = status.value(), title = body)
            else -> body as Problem
        }

        return super.handleExceptionInternal(e, problem, headers, status, request)
    }

    override fun handleHttpMessageNotReadable(
        e: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val rootCause = ExceptionUtils.getRootCause(e)

        if (rootCause is InvalidFormatException) {
            return handleInvalidFormat(rootCause, headers, status, request)
        } else if (rootCause is PropertyBindingException) {
            return handlePropertyBinding(rootCause, headers, status, request)
        }

        val problemType = ProblemType.MESSAGE_NOT_READABLE
        val detail = "The request payload is invalid. Check syntax error!"

        val problem = Problem(
            status = status.value(),
            type = problemType.uri,
            title = problemType.title,
            detail = detail
        )

        return handleExceptionInternal(e, problem, headers, status, request)
    }

    override fun handleTypeMismatch(
        e: TypeMismatchException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        if (e is MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch(e, headers, status, request)
        }

        return super.handleTypeMismatch(e, headers, status, request)
    }

    override fun handleNoHandlerFoundException(
        e: NoHandlerFoundException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val problemType = ProblemType.RESOURCE_NOT_FOUND
        val detail = "The resource '${e.requestURL}', which you tried to access, is non-existent."

        val problem = Problem(
            status = status.value(),
            type = problemType.uri,
            title = problemType.title,
            detail = detail
        )

        return handleExceptionInternal(e, problem, headers, status, request)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusiness(e: BusinessException, request: WebRequest): ResponseEntity<Any> {
        val headers = HttpHeaders()
        val status = HttpStatus.BAD_REQUEST
        val problemType = ProblemType.BUSINESS_ERROR
        val detail = e.message

        val problem = Problem(
            status = status.value(),
            type = problemType.uri,
            title = problemType.title,
            detail = detail
        )

        return handleExceptionInternal(e, problem, headers, status, request)
    }

    @ExceptionHandler(EntityInUseException::class)
    fun handleEntityInUse(e: EntityInUseException, request: WebRequest): ResponseEntity<Any> {
        val headers = HttpHeaders()
        val status = HttpStatus.CONFLICT
        val problemType = ProblemType.ENTITY_IN_USE
        val detail = e.message

        val problem = Problem(
            status = status.value(),
            type = problemType.uri,
            title = problemType.title,
            detail = detail
        )

        return handleExceptionInternal(e, problem, headers, status, request)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFound(e: EntityNotFoundException, request: WebRequest): ResponseEntity<Any> {
        val headers = HttpHeaders()
        val status = HttpStatus.NOT_FOUND
        val problemType = ProblemType.RESOURCE_NOT_FOUND
        val detail = e.message

        val problem = Problem(
            status = status.value(),
            type = problemType.uri,
            title = problemType.title,
            detail = detail
        )

        return handleExceptionInternal(e, problem, headers, status, request)
    }

    private fun handleInvalidFormat(
        e: InvalidFormatException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val path = e.path.joinToString(".") { it.fieldName }
        val problemType = ProblemType.MESSAGE_NOT_READABLE
        val detail = "Property '$path' has been assigned the value '${e.value}', " +
                "which is of an invalid type. Correct and enter a value compatible with type ${e.targetType.simpleName}."

        val problem = Problem(
            status = status.value(),
            type = problemType.uri,
            title = problemType.title,
            detail = detail
        )

        return handleExceptionInternal(e, problem, headers, status, request)
    }

    private fun handlePropertyBinding(
        e: PropertyBindingException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val path = e.path.joinToString(".") { it.fieldName }
        val problemType = ProblemType.MESSAGE_NOT_READABLE
        val detail = "Property '$path' does not exist. " +
                "Please correct or remove this property and try again."

        val problem = Problem(
            status = status.value(),
            type = problemType.uri,
            title = problemType.title,
            detail = detail
        )

        return handleExceptionInternal(e, problem, headers, status, request)
    }

    private fun handleMethodArgumentTypeMismatch(
        e: MethodArgumentTypeMismatchException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val problemType = ProblemType.INVALID_PARAMETER
        val detail = "The URL parameter '${e.name}' was assigned the value '${e.value}', " +
                "which is of an invalid type. Please correct and enter a value compatible with the type ${e.requiredType?.simpleName}."

        val problem = Problem(
            status = status.value(),
            type = problemType.uri,
            title = problemType.title,
            detail = detail
        )

        return handleExceptionInternal(e, problem, headers, status, request)
    }

}
