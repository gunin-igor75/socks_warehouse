package sky.pro.socks_warehouse.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SocksColorConstraintValidator implements ConstraintValidator<ValidationSocksColors, String> {

    private Set<String> colors;


    @Override
    public void initialize(ValidationSocksColors constraintAnnotation) {
        colors = Stream.of(constraintAnnotation.valueColors()).collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return colors.contains(value);
    }
}
