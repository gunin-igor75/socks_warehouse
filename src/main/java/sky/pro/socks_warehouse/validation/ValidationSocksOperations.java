package sky.pro.socks_warehouse.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SocksOperationConstraintValidator.class)
public @interface ValidationSocksOperations {

    String[] valueOperations();

    String message() default "Операция не допустима ${validatedValue}, может быть: {valueOperations} ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
