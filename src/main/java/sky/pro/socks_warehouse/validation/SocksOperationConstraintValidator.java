package sky.pro.socks_warehouse.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SocksOperationConstraintValidator implements ConstraintValidator<ValidationSocksOperations, String> {

    private Set<String> operations;


    @Override
    public void initialize(ValidationSocksOperations constraintAnnotation) {
        operations = Stream.of(constraintAnnotation.valueOperations()).collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return operations.contains(value);
    }
}
