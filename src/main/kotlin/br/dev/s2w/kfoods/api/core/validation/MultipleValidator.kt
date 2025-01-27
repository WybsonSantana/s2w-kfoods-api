package br.dev.s2w.kfoods.api.core.validation

import java.math.BigDecimal
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class MultipleValidator : ConstraintValidator<Multiple, Number> {

    private var multipleNumber: Int? = null

    override fun initialize(constraintAnnotation: Multiple?) {
        multipleNumber = constraintAnnotation?.number
    }

    override fun isValid(value: Number?, context: ConstraintValidatorContext?): Boolean {
        val decimalValue = value?.toDouble()?.let { BigDecimal.valueOf(it) }
            ?: return false
        val decimalMultiple = multipleNumber?.toDouble()?.let { BigDecimal.valueOf(it) }
            ?: return false

        return decimalValue.remainder(decimalMultiple).compareTo(BigDecimal.ZERO) == 0
    }

}
