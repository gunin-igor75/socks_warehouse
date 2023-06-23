package sky.pro.socks_warehouse.validation;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

/**
 * Клас который интегрирует кастомную валидацию в spring
 */
public class SelfValidated {
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * При появлении ошибок валидации пробрасывает исключение
     */
    public final void validateSelf() {
        Set<ConstraintViolation<SelfValidated>> constraintViolations = VALIDATOR.validate(this);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
