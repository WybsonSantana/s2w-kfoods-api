package br.dev.s2w.kfoods.api.core.validation

import org.springframework.beans.BeanUtils
import java.math.BigDecimal
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.ValidationException

class FreeDeliveryFeeIncludesDescriptionValidator
    : ConstraintValidator<FreeDeliveryFeeIncludesDescription, Any> {

    private lateinit var fieldValue: String
    private lateinit var fieldDescription: String
    private lateinit var requiredDescription: String

    override fun initialize(constraintAnnotation: FreeDeliveryFeeIncludesDescription?) {
        fieldValue = constraintAnnotation!!.fieldValue
        fieldDescription = constraintAnnotation.fieldDescription
        requiredDescription = constraintAnnotation.requiredDescription
    }

    override fun isValid(objectValidation: Any?, context: ConstraintValidatorContext?): Boolean {
        try {
            val value = BeanUtils.getPropertyDescriptor(objectValidation!!::class.java, fieldValue)
                ?.readMethod?.invoke(objectValidation)
                ?.let { it as BigDecimal } ?: return false

            val description = BeanUtils.getPropertyDescriptor(objectValidation::class.java, fieldDescription)
                ?.readMethod?.invoke(objectValidation)
                ?.let { it as String } ?: return false

            return when (value) {
                BigDecimal.ZERO -> description.contains(requiredDescription, ignoreCase = true)
                else -> true
            }
        } catch (e: Exception) {
            throw ValidationException(e)
        }
    }

}
