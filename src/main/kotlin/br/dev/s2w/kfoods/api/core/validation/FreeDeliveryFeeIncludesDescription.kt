package br.dev.s2w.kfoods.api.core.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.reflect.KClass

@Target(CLASS)
@Retention(RUNTIME)
@Constraint(validatedBy = [FreeDeliveryFeeIncludesDescriptionValidator::class])
annotation class FreeDeliveryFeeIncludesDescription(
    val message: String = "Invalid Delivery Fee",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val fieldValue: String,
    val fieldDescription: String,
    val requiredDescription: String
)