package sky.pro.socks_warehouse.validation;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

public class SelfValidated {
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    public final void validateSelf() {
        Set<ConstraintViolation<SelfValidated>> constraintViolations = VALIDATOR.validate(this);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
