package br.dev.s2w.kfoods.api.adapter.exceptionhandler

import br.dev.s2w.kfoods.api.domain.exception.BusinessException
import br.dev.s2w.kfoods.api.domain.exception.EntityInUseException
import br.dev.s2w.kfoods.api.domain.exception.EntityNotFoundException
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.fasterxml.jackson.databind.exc.PropertyBindingException
import org.apache.commons.lang3.exception.ExceptionUtils
import org.springframework.beans.TypeMismatchException
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class AdapterExceptionHandler(
    private val messageSource: MessageSource
) : ResponseEntityExceptionHandler() {

    private val genericUserMessage = "An unexpected internal system error has occurred. " +
            "Please try again and if the problem persists, contact your system administrator."

    override fun handleExceptionInternal(
        e: Exception,
        body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val problem = when (body) {
            null -> Problem(status = status.value(), title = status.reasonPhrase, userMessage = genericUserMessage)
            is String -> Problem(status = status.value(), title = body, userMessage = genericUserMessage)
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

        val problem = createProblem(status, problemType, detail)
            .copy(userMessage = genericUserMessage)

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

        val problem = createProblem(status, problemType, detail)
            .copy(userMessage = genericUserMessage)

        return handleExceptionInternal(e, problem, headers, status, request)
    }

    override fun handleMethodArgumentNotValid(
        e: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val problemType = ProblemType.INVALID_DATA
        val detail = "One or more fields are invalid. Fill in correctly and try again!"

        val problemObjects = e.bindingResult.allErrors
            .map { objectError ->
                val message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale())
                val name = when (objectError) {
                    is FieldError -> objectError.field
                    else -> objectError.objectName
                }

                Problem.Object(
                    name = name,
                    userMessage = message
                )
            }

        val problem = createProblem(status, problemType, detail)
            .copy(userMessage = detail, objects = problemObjects)

        return handleExceptionInternal(e, problem, headers, status, request)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusiness(e: BusinessException, request: WebRequest): ResponseEntity<Any> {
        val headers = HttpHeaders()
        val status = HttpStatus.BAD_REQUEST
        val problemType = ProblemType.BUSINESS_ERROR
        val detail = e.message.toString()

        val problem = createProblem(status, problemType, detail)
            .copy(userMessage = genericUserMessage)

        return handleExceptionInternal(e, problem, headers, status, request)
    }

    @ExceptionHandler(EntityInUseException::class)
    fun handleEntityInUse(e: EntityInUseException, request: WebRequest): ResponseEntity<Any> {
        val headers = HttpHeaders()
        val status = HttpStatus.CONFLICT
        val problemType = ProblemType.ENTITY_IN_USE
        val detail = e.message.toString()

        val problem = createProblem(status, problemType, detail)
            .copy(userMessage = genericUserMessage)

        return handleExceptionInternal(e, problem, headers, status, request)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFound(e: EntityNotFoundException, request: WebRequest): ResponseEntity<Any> {
        val headers = HttpHeaders()
        val status = HttpStatus.NOT_FOUND
        val problemType = ProblemType.RESOURCE_NOT_FOUND
        val detail = e.message.toString()

        val problem = createProblem(status, problemType, detail)
            .copy(userMessage = genericUserMessage)

        return handleExceptionInternal(e, problem, headers, status, request)
    }

    @ExceptionHandler(Exception::class)
    fun handleUncaught(e: Exception, request: WebRequest): ResponseEntity<Any> {
        val headers = HttpHeaders()
        val status = HttpStatus.INTERNAL_SERVER_ERROR
        val problemType = ProblemType.SYSTEM_ERROR
        val detail = "An unexpected internal system error has occurred. " +
                "Please try again and if the problem persists, contact your system administrator."

        e.printStackTrace()

        val problem = createProblem(status, problemType, detail)
            .copy(userMessage = genericUserMessage)

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

        val problem = createProblem(status, problemType, detail)
            .copy(userMessage = genericUserMessage)

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

        val problem = createProblem(status, problemType, detail)
            .copy(userMessage = genericUserMessage)

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

        val problem = createProblem(status, problemType, detail)
            .copy(userMessage = genericUserMessage)

        return handleExceptionInternal(e, problem, headers, status, request)
    }

    private fun createProblem(status: HttpStatus, problemType: ProblemType, detail: String): Problem =
        Problem(
            status = status.value(),
            type = problemType.uri,
            title = problemType.title,
            detail = detail,
        )

}
