package br.dev.s2w.kfoods.api.core.validation

import javax.validation.Constraint
import javax.validation.OverridesAttribute
import javax.validation.Payload
import javax.validation.constraints.PositiveOrZero
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@Target(FUNCTION, FIELD, ANNOTATION_CLASS, CONSTRUCTOR, VALUE_PARAMETER, TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = [])
@PositiveOrZero
annotation class DeliveryFee(
    @get:OverridesAttribute(constraint = PositiveOrZero::class, name = "message")
    val message: String = "{deliveryFee.invalid}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)